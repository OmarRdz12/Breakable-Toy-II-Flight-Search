package encora.breakableII.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import encora.breakableII.backend.dao.FlightSearchDao;
import encora.breakableII.backend.entities.*;
import encora.breakableII.backend.models.*;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightServiceImpl implements FlightService{

    private final ApiAuth apiAuth;
    private final FlightSearchDao flightSearchDao;

    @Value("${amadeus.api.flights}")
    private String urlBase;

    @Value("${amadeus.api.airport}")
    private String urlBaseAirport;

    public FlightServiceImpl(ApiAuth apiAuth, FlightSearchDao flightSearchDao ) {
        this.apiAuth = apiAuth;
        this.flightSearchDao = flightSearchDao;
    }

    @Override
    @RateLimiter(name="apiRateLimiter")
    public List<FlightOffer> searchFlights(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop, String currencyCode, String arrivalDate) {
        RestTemplate restTemplate = new RestTemplate();
        String token = apiAuth.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = arrivalDate.isEmpty() ? urlBase  + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop + "&currencyCode=" + currencyCode : urlBase  + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop + "&currencyCode=" + currencyCode + "&returnDate=" + arrivalDate;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<FlightOffer> frontendResponse = new ArrayList<FlightOffer>();

        try {
            FlightResponse responseMap = mapper.readValue(response.getBody(), FlightResponse.class);
            if (responseMap.getMeta().getCount() == 0)
                return frontendResponse;
            List<FlightAmadeus> flightOffers = responseMap.getData();
            Dictionaries dictionary = responseMap.getDictionaries();

            Map<String, Location> locations = dictionary.getLocations();
            Map<String, String> cities = new HashMap<>();
            for (String key : locations.keySet()) {
                try {
                    cities.put(key, getLocations(key).getFirst().getCity());
                } catch (Exception e) {
                    cities.put(key, "");
                }

            }

            for (FlightAmadeus offer : flightOffers) {
                FlightOffer flightOffer = new FlightOffer();
                flightOffer.setId(offer.getId());
                flightOffer.setPricePerTraveler(offer.getTravelerPricings().getFirst().getPrice().getTotal());
                flightOffer.setPriceTotal(offer.getPrice().getTotal());
                List<Itinerary> itineraries = offer.getItineraries();
                List<IndividualFlight> individualFlights = new ArrayList<>();
                Duration totalDuration = Duration.ZERO;
                int contItinerary = 0;
                for (Itinerary itinerary : itineraries) {
                    IndividualFlight individualFlight = new IndividualFlight();
                    individualFlight.setCurrency(currencyCode);
                    List<Segment> segments = itinerary.getSegments();
                    individualFlight.setDepartureDate(LocalDateTime.parse(segments.getFirst().getDeparture().getAt()));
                    individualFlight.setArrivalDateTime(LocalDateTime.parse(segments.getLast().getArrival().getAt()));
                    individualFlight.setDepartureAirport(segments.getFirst().getDeparture().getIataCode());
                    individualFlight.setDepartureAirportName(cities.get(segments.getFirst().getDeparture().getIataCode()));
                    individualFlight.setArrivalAirport(segments.getLast().getArrival().getIataCode());
                    individualFlight.setArrivalAirportName(cities.get(segments.getLast().getArrival().getIataCode()));
                    individualFlight.setDuration(itinerary.getDuration());
                    totalDuration = totalDuration.plus(Duration.parse(itinerary.getDuration()));
                    individualFlight.setAirlineCode(segments.getFirst().getCarrierCode());
                    try {
                        individualFlight.setAirlineName(dictionary.getCarriers().get(segments.getFirst().getCarrierCode()));
                        individualFlight.setCarrierCode(segments.getFirst().getOperating().getCarrierCode());
                    } catch (Exception e) {
                        individualFlight.setAirlineName("");
                        individualFlight.setCarrierCode("");
                    }

                    individualFlight.setCarrierName(dictionary.getCarriers().get(segments.getFirst().getCarrierCode()));
                    List<Stops> stops = new ArrayList<Stops>();
                    for (Segment segment : segments)
                    {
                        Stops stop = new Stops();
                        stop.setAirlineCode(segment.getCarrierCode());
                        stop.setAirlineName(dictionary.getCarriers().get(segment.getCarrierCode()));
                        try {
                            stop.setCarrierAirlineCode(segment.getOperating().getCarrierCode());
                            stop.setCarrierAirlineName(dictionary.getCarriers().get(segment.getOperating().getCarrierCode()));
                        } catch (Exception e) {
                            stop.setCarrierAirlineCode("");
                            stop.setCarrierAirlineName("");
                        }
                        stop.setArrivalAirportCode(segment.getArrival().getIataCode());
                        stop.setArrivalAirportName(cities.get(segment.getArrival().getIataCode()));
                        stop.setDepartureAirportName(cities.get(segment.getDeparture().getIataCode()));
                        stop.setDepartureAirportCode(segment.getDeparture().getIataCode());
                        stop.setDepartureTime(segment.getDeparture().getAt());
                        stop.setArrivalTime(segment.getArrival().getAt());
                        stop.setDurationTravel(segment.getDuration());
                        stop.setFlightNumber(segment.getNumber());
                        stop.setAircraft(segment.getAircraft().getCode());
                        stop.setId(segment.getId());
                        stops.add(stop);
                    }
                    for (int i = 0; i < itinerary.getSegments().size(); i++) {
                        stops.get(i).setFareDetailsBySegment(offer.getTravelerPricings().getFirst().getFareDetailsBySegment().get(i + contItinerary));
                    }
                    contItinerary += itinerary.getSegments().size();
                    individualFlight.setStops(stops);
                    individualFlights.add(individualFlight);
                }
                flightOffer.setTotalDuration(totalDuration);
                flightOffer.setIndividualFlights(individualFlights);
                frontendResponse.add(flightOffer);

            }
            flightSearchDao.setFlights(frontendResponse);
            return frontendResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return frontendResponse;
    }


    @Override
    @RateLimiter(name="apiRateLimiter")
    @Bulkhead(name="apiRateLimiter")
    public List<Airport> getLocations(String name) {
        if(name.isEmpty()) {
            List<Airport> emptyList = new ArrayList<Airport>();
            return emptyList;
        }
        String token = apiAuth.getAccessToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = urlBaseAirport + "keyword=" + name;
        HttpEntity<String> entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if(response.getStatusCodeValue() != 200) {
            return flightSearchDao.getLocation(name);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            AirportResponse res = mapper.readValue(response.getBody(), AirportResponse.class);
            if(res.getMeta().getCount() == 0)
                return flightSearchDao.getLocation(name);
            List<AirportAmadeus> airportsAmadeus = res.getData();
            List<Airport> airports = new ArrayList<Airport>();
            for (AirportAmadeus airportAmadeus : airportsAmadeus) {
                Airport airport = new Airport();
                airport.setCode(airportAmadeus.getIataCode());
                airport.setCity(airportAmadeus.getName());
                airports.add(airport);
            }
            return airports;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return flightSearchDao.getLocation(name);
    }

    @Override
    public List<FlightOffer> sortFlights(String priceSort, String durationSort) {
        return flightSearchDao.sortFlights(priceSort, durationSort);
    }
}

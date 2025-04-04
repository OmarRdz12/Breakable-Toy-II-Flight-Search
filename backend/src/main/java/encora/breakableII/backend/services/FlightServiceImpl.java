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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightServiceImpl implements FlightService {

    private final ApiAuth apiAuth;
    private final FlightSearchDao flightSearchDao;

    @Value("${amadeus.api.flights}")
    private String urlBase;

    @Value("${amadeus.api.airport}")
    private String urlBaseAirport;

    public FlightServiceImpl(ApiAuth apiAuth, FlightSearchDao flightSearchDao) {
        this.apiAuth = apiAuth;
        this.flightSearchDao = flightSearchDao;
    }

    @Override
    @RateLimiter(name = "apiRateLimiter")
    public ResponseService searchFlights(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop, String currencyCode, String arrivalDate) {
        RestTemplate restTemplate = new RestTemplate();
        String token = apiAuth.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = arrivalDate.isEmpty() ? urlBase + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop + "&currencyCode=" + currencyCode : urlBase + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop + "&currencyCode=" + currencyCode + "&returnDate=" + arrivalDate;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseService responseService = new ResponseService();
        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper mapper = new ObjectMapper();
            List<FlightOffer> frontendResponse = new ArrayList<FlightOffer>();
            FlightResponse responseMap = mapper.readValue(response.getBody(), FlightResponse.class);
            if (responseMap.getMeta().getCount() == 0) {
                responseService.setFlightOffers(frontendResponse);
                responseService.setStatus(404);
                return responseService;
            }

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
                    for (Segment segment : segments) {
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
            responseService.setFlightOffers(frontendResponse);
            responseService.setStatus(200);
            return responseService;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (HttpServerErrorException e) {
            if (e.getStatusCode().value() == 500) {
                System.err.println("Error 500 Amadeus, internal error");
                responseService.setFlightOffers(new ArrayList<FlightOffer>());
                responseService.setStatus(502);
                return responseService;
            }
            responseService.setFlightOffers(new ArrayList<FlightOffer>());
            responseService.setStatus(500);
            return responseService;
        } catch (HttpClientErrorException e) {
            responseService.setFlightOffers(new ArrayList<FlightOffer>());
            responseService.setStatus(400);
            return responseService;
        }
        responseService.setFlightOffers(new ArrayList<FlightOffer>());
        responseService.setStatus(501);
        return responseService;
    }


    @Override
    @RateLimiter(name = "apiRateLimiter")
    @Bulkhead(name = "apiRateLimiter")
    public List<Airport> getLocations(String name) {
        if (name.isEmpty()) {
            List<Airport> emptyList = new ArrayList<Airport>();
            return emptyList;
        }
        String token = apiAuth.getAccessToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = urlBaseAirport + "keyword=" + name;
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            if (response.getStatusCode().value() != 200) {
                return flightSearchDao.getLocation(name);
            }
            ObjectMapper mapper = new ObjectMapper();
            AirportResponse res = mapper.readValue(response.getBody(), AirportResponse.class);
            if (res.getMeta().getCount() == 0)
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

        } catch (HttpServerErrorException e) {
            if (e.getStatusCode().value() == 500) {
                System.err.println("Error 500 on Amadeus: " + e.getMessage());
                return flightSearchDao.getLocation(name);
            }
            return flightSearchDao.getLocation(name);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 423) {
                System.err.println("Error 423 on Amadeus: " + e.getMessage());
                return flightSearchDao.getLocation(name);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error");
            return flightSearchDao.getLocation(name);
        }
        return flightSearchDao.getLocation(name);
    }

    @Override
    public List<FlightOffer> sortFlights(String priceSort, String durationSort, List<FlightOffer> flightOffers) {
        List<FlightOffer> sortedFlights = new ArrayList<>(flightOffers);
        if (priceSort.equals("") && durationSort.equals("")) {
            return flightOffers;
        } else {
            if (priceSort.equals("asc") && durationSort.equals("")) {
                return sortedFlights.stream().sorted(Comparator.comparing(offer -> Double.parseDouble(offer.getPricePerTraveler()))).toList();
            } else {
                if (priceSort.equals("desc") && durationSort.equals("")) {
                    return sortedFlights.stream().sorted(Comparator.comparing(offer -> Double.parseDouble(offer.getPricePerTraveler()), Comparator.reverseOrder())).toList();
                } else {
                    if (priceSort.equals("") && durationSort.equals("asc")) {
                        return sortedFlights.stream().sorted(Comparator.comparing(FlightOffer::getTotalDuration)).toList();
                    } else {
                        if (priceSort.equals("") && durationSort.equals("desc")) {
                            return sortedFlights.stream().sorted(Comparator.comparing(FlightOffer::getTotalDuration, Comparator.reverseOrder())).toList();
                        } else {
                            if (priceSort.equals("asc") && durationSort.equals("asc")) {
                                return sortedFlights.stream().sorted(Comparator.comparing((FlightOffer offer) -> Double.parseDouble(offer.getPricePerTraveler()))
                                        .thenComparing(FlightOffer::getTotalDuration)).toList();
                            } else {
                                if (priceSort.equals("desc") && durationSort.equals("desc")) {
                                    return sortedFlights.stream().sorted(Comparator.comparing((FlightOffer offer) -> Double.parseDouble(offer.getPricePerTraveler()), Comparator.reverseOrder())
                                            .thenComparing(FlightOffer::getTotalDuration, Comparator.reverseOrder())).toList();
                                } else {
                                    if (priceSort.equals("asc") && durationSort.equals("desc")) {
                                        return sortedFlights.stream().sorted(Comparator.comparing((FlightOffer offer) -> Double.parseDouble(offer.getPricePerTraveler()))
                                                .thenComparing(FlightOffer::getTotalDuration, Comparator.reverseOrder())).toList();
                                    } else {
                                        return sortedFlights.stream().sorted(Comparator.comparing((FlightOffer offer) -> Double.parseDouble(offer.getPricePerTraveler()), Comparator.reverseOrder())
                                                .thenComparing(FlightOffer::getTotalDuration)).toList();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

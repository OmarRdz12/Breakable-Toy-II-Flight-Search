package encora.breakableII.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import encora.breakableII.backend.dao.FlightSearchDao;
import encora.breakableII.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private ApiAuthImp apiAuthImp;

    private final RestTemplate restTemplate = new RestTemplate();
    FlightSearchDao flightSearchDao;

    @Value("${amadeus.api.flights}")
    private String urlBase;

    @Value("${amadeus.api.airport}")
    private String urlBaseAirport;

    public FlightServiceImpl(FlightSearchDao flightSearchDao) {
        this.flightSearchDao = flightSearchDao;
    }

    @Override
    public List<FlightOffer> searchLocations(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop) {
        String token = apiAuthImp.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = urlBase  + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<FlightOffer> frontendResponse = new ArrayList<FlightOffer>();
        try {
            FlightResponse res = mapper.readValue(response.getBody(), FlightResponse.class);
            if (res.getMeta().getCount() == 0)
                return frontendResponse;
            List<FlightAmadeus> flightOffers = res.getData();

            for (FlightAmadeus offer : flightOffers) {
                FlightOffer flightOffer = new FlightOffer();
                List<Itinerary> itineraries = offer.getItineraries();
                List<Segment> segments = itineraries.getFirst().getSegments();
                flightOffer.setDepartureDate(LocalDateTime.parse(segments.getFirst().getDeparture().getAt()));
                flightOffer.setArrivalDateTime(LocalDateTime.parse(segments.getLast().getArrival().getAt()));
                flightOffer.setDepartureAirport(segments.getFirst().getDeparture().getIataCode());
                flightOffer.setArrivalAirport(segments.getLast().getArrival().getIataCode());
                flightOffer.setPricePerTraveler(offer.getTravelerPricings().getFirst().getPrice().getTotal());
                flightOffer.setPriceTotal(offer.getPrice().getTotal());

                for(String codes : offer.getValidatingAirlineCodes())
                {
                    flightOffer.setAirlineCode(codes);
                }
                for (Segment segment : segments)
                {
                    //System.out.println("Operating: " + segment.getCarrierCode() );
                    flightOffer.setCarrierCode(segment.getCarrierCode());
                }
                frontendResponse.add(flightOffer);
            }
            return frontendResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return frontendResponse;
    }

    @Override
    public List<Airport> getLocations(String name) {
        String token = apiAuthImp.getAccessToken();
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
}

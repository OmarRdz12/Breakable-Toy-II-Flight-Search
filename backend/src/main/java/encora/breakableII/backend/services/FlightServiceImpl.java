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

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private ApiAuthImp apiAuthImp;

    private final RestTemplate restTemplate = new RestTemplate();
    FlightSearchDao flightSearchDao;

    @Value("${amadeus.api.flights}")
    private String urlBase;

    public FlightServiceImpl(FlightSearchDao flightSearchDao) {
        this.flightSearchDao = flightSearchDao;
    }

    @Override
    public String searchLocations(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop) {
        String token = apiAuthImp.getAccessToken();
        String urlBase = "https://test.api.amadeus.com/v2/shopping/flight-offers?";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        String url = urlBase  + "originLocationCode=" + originLocationCode + "&destinationLocationCode=" + destinationCode + "&departureDate=" + departureDate + "&adults=" + adults + "&nonStop=" + nonStop;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            FlightResponse res = mapper.readValue(response.getBody(), FlightResponse.class);
            if (res.getMeta().getCount() == 0)
                return "No hay vuelos disponibles";
            List<FlightAmadeus> flightOffers = res.getData();
            for (FlightAmadeus offer : flightOffers) {
                List<Itinerary> itineraries = offer.getItineraries();
                System.out.println("Duracion : " + itineraries.getFirst().getDuration());
                List<Segment> segments = itineraries.getFirst().getSegments();
                System.out.println("Hora de Salida: " +  segments.getFirst().getDeparture().getAt());
                System.out.println("Lugar de Salida: " +  segments.getFirst().getDeparture().getIataCode());
                System.out.println("Hora de llegada:" +segments.getLast().getArrival().getAt());
                System.out.println("Lugar de llegada:" +segments.getLast().getArrival().getIataCode());

                System.out.println("Precio por viajero: " + offer.getTravelerPricings().getFirst().getPrice().getTotal() );
                System.out.println("Precio total: " + offer.getPrice().getTotal());
                for(String codes : offer.getValidatingAirlineCodes())
                {
                    System.out.println("Aerolinea: " + codes);
                }
                for (Segment segment : segments)
                {
                    System.out.println("Parada en " + segment.getArrival().getIataCode() + "a las " + segment.getArrival().getAt());
                    System.out.println("Operating: " + segment.getCarrierCode() );
                }

            }
            return "funciona";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    @Override
    public List<Airport> getLocations(String name) {
        return flightSearchDao.getLocation(name);
    }
}

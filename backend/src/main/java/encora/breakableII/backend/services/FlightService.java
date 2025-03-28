package encora.breakableII.backend.services;

import encora.breakableII.backend.models.Airport;

import java.util.List;

public interface FlightService {
    String searchLocations(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop);
    List<Airport> getLocations(String name);
}

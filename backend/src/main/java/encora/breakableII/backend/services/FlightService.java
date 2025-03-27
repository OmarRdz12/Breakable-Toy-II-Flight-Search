package encora.breakableII.backend.services;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;

import java.util.List;

public interface FlightService {
    List<FlightOffer> searchLocations(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop);
    List<Airport> getLocations(String name);
}

package encora.breakableII.backend.services;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;

import java.util.List;

public interface FlightService {
    List<FlightOffer> searchFlights(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop, String currencyCode, String arrivalDate);
    List<Airport> getLocations(String name);
    List<FlightOffer> sortFlights(String priceSort, String durationSort, List<FlightOffer> flightOffers);
}

package encora.breakableII.backend.dao;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;

import java.util.List;

public interface FlightSearchDao {
    List<Airport> getLocation(String name);
    List<FlightOffer> sortFlights(String priceSort, String durationSort);
    void setFlights(List<FlightOffer> offers);
}

package encora.breakableII.backend.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class FlightOffer {
    private List<IndividualFlight> individualFlights;
    private String id;
    private String pricePerTraveler;
    private String priceTotal;
    private Duration totalDuration;

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Duration totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<IndividualFlight> getIndividualFlights() {
        return individualFlights;
    }

    public void setIndividualFlights(List<IndividualFlight> individualFlights) {
        this.individualFlights = individualFlights;
    }

    public String getPricePerTraveler() {
        return pricePerTraveler;
    }

    public void setPricePerTraveler(String pricePerTraveler) {
        this.pricePerTraveler = pricePerTraveler;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }
}

package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightAmadeus {
    private String id;
    private String source;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private List<Itinerary> itineraries;
    private Price price;
    private List<TravelerPricing> travelerPricings;
    private List<String> validatingAirlineCodes;

    public List<String> getValidatingAirlineCodes() {
        return validatingAirlineCodes;
    }

    public void setValidatingAirlineCodes(List<String> validatingAirlineCodes) {
        this.validatingAirlineCodes = validatingAirlineCodes;
    }

    public List<TravelerPricing> getTravelerPricings() {
        return travelerPricings;
    }

    public void setTravelerPricings(List<TravelerPricing> travelerPricings) {
        this.travelerPricings = travelerPricings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLastTicketingDate() {
        return lastTicketingDate;
    }

    public void setLastTicketingDate(String lastTicketingDate) {
        this.lastTicketingDate = lastTicketingDate;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(int numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

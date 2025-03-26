package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {
    private Site departure;
    private Site arrival;
    private String carrierCode;
    private String number;
    private int numberOfStops;

    public Site getDeparture() {
        return departure;
    }

    public Site getArrival() {
        return arrival;
    }

    public String getNumber() {
        return number;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setDeparture(Site departure) {
        this.departure = departure;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public void setArrival(Site arrival) {
        this.arrival = arrival;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }
}

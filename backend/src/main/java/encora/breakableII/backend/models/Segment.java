package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {
    private Site departure;
    private Site arrival;
    private String carrierCode;
    private String number;
    private int numberOfStops;
    private Operating operating;
    private String duration;
    private Aircraft aircraft;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Operating getOperating() {
        return operating;
    }

    public void setOperating(Operating operating) {
        this.operating = operating;
    }

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

package encora.breakableII.backend.models;

import java.time.LocalDateTime;

public class Stops {
    private String departureAirportName;
    private String departureAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCode;
    private String departureTime;
    private String arrivalTime;
    private String durationTravel;
    private String airlineCode;
    private String airlineName;
    private String flightNumber;
    private String carrierAirlineCode;
    private String carrierAirlineName;
    private String aircraft;
    private String id;
    private FareDetailsBySegment fareDetailsBySegment;

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDurationTravel() {
        return durationTravel;
    }

    public void setDurationTravel(String durationTravel) {
        this.durationTravel = durationTravel;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrierAirlineCode() {
        return carrierAirlineCode;
    }

    public void setCarrierAirlineCode(String carrierAirlineCode) {
        this.carrierAirlineCode = carrierAirlineCode;
    }

    public String getCarrierAirlineName() {
        return carrierAirlineName;
    }

    public void setCarrierAirlineName(String carrierAirlineName) {
        this.carrierAirlineName = carrierAirlineName;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FareDetailsBySegment getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }

    public void setFareDetailsBySegment(FareDetailsBySegment fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }
}

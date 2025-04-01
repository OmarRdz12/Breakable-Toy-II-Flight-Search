package encora.breakableII.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dictionaries {
    private Map<String, String> airCraft;
    private Map<String, String> carriers;
    private Map<String, Location> locations;

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }

    public Map<String, String> getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(Map<String, String> airCraft) {
        this.airCraft = airCraft;
    }

    public Map<String, String> getCarriers() {
        return carriers;
    }

    public void setCarriers(Map<String, String> carriers) {
        this.carriers = carriers;
    }
}

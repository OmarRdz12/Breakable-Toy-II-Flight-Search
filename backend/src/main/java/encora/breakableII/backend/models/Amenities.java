package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amenities {
    private String description;
    @JsonProperty("isChargeable")
    private boolean isChargeable;

    @JsonProperty("isChargeable")
    public boolean isChargeable() {
        return isChargeable;
    }

    @JsonProperty("isChargeable")
    public void setChargeable(boolean chargeable) {
        isChargeable = chargeable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

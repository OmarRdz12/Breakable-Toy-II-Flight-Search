package encora.breakableII.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import encora.breakableII.backend.models.FareDetailsBySegment;
import encora.breakableII.backend.models.Price;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelerPricing {
    Price price;
    List<FareDetailsBySegment> fareDetailsBySegment;

    public List<FareDetailsBySegment> getFareDetailsBySegment() {
        return fareDetailsBySegment;
    }

    public void setFareDetailsBySegment(List<FareDetailsBySegment> fareDetailsBySegment) {
        this.fareDetailsBySegment = fareDetailsBySegment;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightResponse {
    private Meta meta;
    private List<FlightAmadeus> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<FlightAmadeus> getData() {
        return data;
    }

    public void setData(List<FlightAmadeus> data) {
        this.data = data;
    }
}

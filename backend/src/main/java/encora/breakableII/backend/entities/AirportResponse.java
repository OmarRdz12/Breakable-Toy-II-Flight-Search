package encora.breakableII.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportResponse {
    private Meta meta;
    private List<AirportAmadeus> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<AirportAmadeus> getData() {
        return data;
    }

    public void setData(List<AirportAmadeus> data) {
        this.data = data;
    }
}

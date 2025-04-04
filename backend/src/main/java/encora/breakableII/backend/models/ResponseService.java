package encora.breakableII.backend.models;

import java.util.List;

public class ResponseService {
    private List<FlightOffer> flightOffers;
    private int status;

    public List<FlightOffer> getFlightOffers() {
        return flightOffers;
    }

    public void setFlightOffers(List<FlightOffer> flightOffers) {
        this.flightOffers = flightOffers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

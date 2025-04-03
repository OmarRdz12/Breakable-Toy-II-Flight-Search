package encora.breakableII.backend.controllers;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;
import encora.breakableII.backend.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightApiImpl implements FlightApi{
    FlightService flightService;

    public FlightApiImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ResponseEntity<List<Airport>> getLocations(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        if (name == null) {
            throw new ApiException("Name cannot be null", 400);
        }
        return new ResponseEntity<>(flightService.getLocations(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FlightOffer>> getFlights(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop, String currencyCode, String arrivalDate) {
        if (adults == 0 || adults < 0) {
            throw new ApiException("Adults has an invalid value", 400);
        }
        if (originLocationCode == null) {
            throw new ApiException("originLocationCode cannot be null", 400);
        }
        if (destinationCode == null) {
            throw new ApiException("destinationCode cannot be null", 400);
        }
        if (!currencyCode.equals("MXN") && !currencyCode.equals("USD") && !currencyCode.equals("EUR")) {
            throw new ApiException("currencyCode has an invalid value", 400);
        }
        if (!arrivalDate.isEmpty() && LocalDate.parse(arrivalDate).isBefore(LocalDate.parse(departureDate))) {
            throw new ApiException("arrivalDare cannot be after departureDate", 400);
        }
        return new ResponseEntity<>(flightService.searchFlights(originLocationCode, destinationCode, departureDate, adults, nonStop, currencyCode, arrivalDate), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FlightOffer>> sortFlights(String priceSort, String durationSort, List<FlightOffer> flightOffers) {
        return new ResponseEntity<>(flightService.sortFlights(priceSort, durationSort, flightOffers), HttpStatus.OK);
    }
}

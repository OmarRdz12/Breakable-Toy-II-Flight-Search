package encora.breakableII.backend.controllers;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightApiImpl implements FlightApi{
    FlightService flightService;

    public FlightApiImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ResponseEntity<List<Airport>> getLocations(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
         return new ResponseEntity<>(flightService.getLocations(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getFlights(String originLocationCode, String destinationCode, String departureDate, int adults, boolean nonStop) {
        return new ResponseEntity<>(flightService.searchLocations(originLocationCode, destinationCode, departureDate, adults, nonStop), HttpStatus.OK);
    }
}

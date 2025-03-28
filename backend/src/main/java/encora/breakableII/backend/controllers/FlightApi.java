package encora.breakableII.backend.controllers;

import encora.breakableII.backend.models.Airport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FlightApi {
    @GetMapping("/locations")
    ResponseEntity<List<Airport>> getLocations(@RequestParam(name = "name", required = false, defaultValue = "") String name);
    @GetMapping("/flights")
    ResponseEntity<String> getFlights(@RequestParam(name = "originLocationCode", required = true) String originLocationCode,
                                      @RequestParam(name = "destinationLocationCode", required = true) String destinationCode,
                                      @RequestParam(name = "departureDate", required = true) String departureDate,
                                      @RequestParam(name = "adults", required = true) int adults,
                                      @RequestParam(name = "nonStop", required = true) boolean nonStop

    );

}

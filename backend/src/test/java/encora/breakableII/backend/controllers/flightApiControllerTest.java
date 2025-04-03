package encora.breakableII.backend.controllers;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;
import encora.breakableII.backend.models.ResponseService;
import encora.breakableII.backend.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class flightApiControllerTest {
    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightApiImpl flightApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLocations_success() {
        String name = "Madrid";
        List<Airport> airports = Arrays.asList(new Airport("Madrid", "MAD", "España"));
        when(flightService.getLocations(name)).thenReturn(airports);

        ResponseEntity<List<Airport>> response = flightApi.getLocations(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airports, response.getBody());
        verify(flightService, times(1)).getLocations(name);
    }

    @Test
    void getLocations_nullName() {
        ApiException exception = assertThrows(ApiException.class, () ->
                flightApi.getLocations(null));
        assertEquals("Name cannot be null", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
        verify(flightService, never()).getLocations(any());
    }

    @Test
    void getLocations_emptyName_success() {
        String name = "";
        List<Airport> airports = Collections.emptyList();
        when(flightService.getLocations(name)).thenReturn(airports);

        ResponseEntity<List<Airport>> response = flightApi.getLocations(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airports, response.getBody());
        verify(flightService, times(1)).getLocations(name);
    }

    @Test
    void getFlights_success() {
        String origin = "MAD", dest = "BCN", depDate = "2025-05-01", arrDate = "2025-05-05", currency = "EUR";
        int adults = 1;
        boolean nonStop = false;
        List<FlightOffer> offers = Arrays.asList(new FlightOffer());
        ResponseService responseService = new ResponseService();
        responseService.setStatus(200);
        responseService.setFlightOffers(offers);
        when(flightService.searchFlights(origin, dest, depDate, adults, nonStop, currency, arrDate)).thenReturn(responseService);

        ResponseEntity<List<FlightOffer>> response = flightApi.getFlights(origin, dest, depDate, adults, nonStop, currency, arrDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(offers, response.getBody());
        verify(flightService, times(1)).searchFlights(origin, dest, depDate, adults, nonStop, currency, arrDate);
    }

    @Test
    void getFlights_invalidAdults() {
        ApiException exception = assertThrows(ApiException.class, () -> flightApi.getFlights("MAD", "BCN", "2025-05-01", 0, false, "EUR", ""));
        assertEquals("Adults has an invalid value", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
        verify(flightService, never()).searchFlights(any(), any(), any(), anyInt(), anyBoolean(), any(), any());
    }

    @Test
    void getFlights_nullOrigin() {
        ApiException exception = assertThrows(ApiException.class, () -> flightApi.getFlights(null, "BCN", "2025-05-01", 1, false, "EUR", ""));
        assertEquals("originLocationCode cannot be null", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
    }

    @Test
    void getFlights_invalidCurrency() {
        ApiException exception = assertThrows(ApiException.class, () -> flightApi.getFlights("MAD", "BCN", "2025-05-01", 1, false, "YEN", ""));
        assertEquals("currencyCode has an invalid value", exception.getMessage());
        assertEquals(400, exception.getStatusCode());
    }
    

}

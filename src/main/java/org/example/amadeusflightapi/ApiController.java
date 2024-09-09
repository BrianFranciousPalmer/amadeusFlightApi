package org.example.amadeusflightapi;

import com.amadeus.exceptions.ResponseException;
//import com.amadeus.resources.*;
import com.amadeus.resources.*;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class ApiController {

    @GetMapping("/")
    public String hello() {
        return "Hello World Travel!";
    }

    @GetMapping("/locations")
    public Location[] locations(@RequestParam(required=true) String keyword) throws ResponseException {
        return AmadeusConnect.INSTANCE.location(keyword);
    }

    // step 5
    @GetMapping("/flights")
    public FlightOfferSearch[] flights(@RequestParam(required=true) String origin,
                                       @RequestParam(required=true) String destination,
                                       @RequestParam(required=true) String departDate,
                                       @RequestParam(required=true) String adults,
                                       @RequestParam(required = false) String returnDate)
            throws ResponseException {
        return AmadeusConnect.INSTANCE.flights(origin, destination, departDate, adults, returnDate);
    }

    // step 6
    @PostMapping("/confirm")
    public FlightPrice confirm(@RequestBody(required=true) FlightOfferSearch search) throws ResponseException {
        return AmadeusConnect.INSTANCE.confirm(search);
    }

    // step 7
    @PostMapping("/traveler")
    public Traveler traveler(@RequestBody(required=true) JsonObject travelerInfo) {
        return DatabaseConnect.traveler(travelerInfo.get("data").getAsJsonObject());
    }

}
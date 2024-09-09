package org.example.amadeusflightapi;

import org.example.amadeusflightapi.AmadeusFlightService;
import org.springframework.stereotype.Controller;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightSearchController {

   // @Autowired
    private AmadeusFlightService amadeusFlightService;

    @GetMapping("/searchFlights")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam String departureDate,
                                //@RequestParam String arrivalDate,
                                @RequestParam String duration,
                                Model model) {
        try {
            FlightOfferSearch[] flightOffers = amadeusFlightService.searchFlights(origin, destination, departureDate);
            model.addAttribute("flightOffers", flightOffers);
        } catch (ResponseException e) {
            model.addAttribute("error", "error occurred while searching flights" + e.getMessage());
        }
        return "flightSearch";

    }
}

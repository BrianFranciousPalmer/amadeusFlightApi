package org.example.amadeusflightapi;


import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import org.example.amadeusflightapi.AmadeusConnect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping(value="/api")
public class ApiController {

    @GetMapping("/")
    public String hello() {
        return "Hello World Travel!";
    }

    @GetMapping("/flightSearch")
        public String flightSearch () {
        return  "flightSearch";
    }

    @GetMapping("/locations")
    public Location[] locations(@RequestParam(required=true) String keyword) throws ResponseException {
        return AmadeusConnect.INSTANCE.location(keyword);
    }


}
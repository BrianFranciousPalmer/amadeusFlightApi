package org.example.amadeusflightapi;

import org.springframework.stereotype.Service;
import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import org.springframework.beans.factory.annotation.Value;


@Service
public class AmadeusFlightService {
    private final Amadeus amadeus;

    public AmadeusFlightService(@Value("${amadeus.api.key}") String apiKey,
                                @Value("${amadeus.api.secret}") String apiSecret)  {

        this.amadeus = Amadeus.builder(apiKey, apiSecret).build();
    }
    // Searches for flights based on parameters: origin, destination, and departure date.

    public FlightOfferSearch[] searchFlights(String origin, String destination, String departureDate) throws ResponseException {
         return amadeus.shopping.flightOffersSearch.get(
                 com.amadeus.Params
                         .with("originLocationCode", origin)
                         .and("destinationLocationCode", destination)
                         .and("departureDate", departureDate)
                         .and("adults", 1)
                         .and("children", 1)
         );
     }


}

package org.example.amadeusflightapi;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;
import com.google.gson.JsonObject;

enum AmadeusConnect {
    INSTANCE;
    private Amadeus amadeus;
    private AmadeusConnect() {
        this.amadeus = Amadeus

                .builder("06TOKZfxqSPMpu32WnIGgIXRlk7aHV8x", "2YzRkNj9SeJUSl9S")
                .build();
    }
    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subType", Locations.AIRPORT));
    }
    public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origin)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", departDate)
                        .and("returnDate", returnDate)
                        .and("adults", adults)
                        .and("max", 3));
    }
    public FlightPrice confirm(FlightOfferSearch offer) throws ResponseException {
        return amadeus.shopping.flightOffersSearch.pricing.post(offer);
    }
    public FlightOrder order(JsonObject order) throws ResponseException {
        return amadeus.booking.flightOrders.post(order);
    }
}
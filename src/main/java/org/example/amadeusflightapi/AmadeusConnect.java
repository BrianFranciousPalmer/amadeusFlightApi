package org.example.amadeusflightapi;


import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;
enum AmadeusConnect {
    INSTANCE;
    private final Amadeus amadeus;
    private AmadeusConnect() {
        this.amadeus = Amadeus

                .builder("06TOKZfxqSPMpu32WnIGgIXRlk7aHV8x", "2YzRkNj9SeJUSl9S")
                .build();
    }
    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subType", Locations.AIRPORT));
 
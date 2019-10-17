package com.tsystems.transportinfo.service;

import com.graphhopper.api.GraphHopperGeocoding;
import com.graphhopper.api.model.GHGeocodingRequest;
import com.graphhopper.api.model.GHGeocodingResponse;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphHopperTest {

    @Test
    public void warehouseFoundByCoordinates() {
        GraphHopperGeocoding graphHopperGeocoding = new GraphHopperGeocoding();
        graphHopperGeocoding.setKey("9dcf0a7e-ee94-4b91-8966-ca7b35411a00");

        GHPoint point = new GHPoint(48.7525249, 18.1450552);
        GHGeocodingRequest request = new GHGeocodingRequest(true, point, "", "en", 5, "default", 5000L);

        GHGeocodingResponse response = graphHopperGeocoding.geocode(request);
        String name = response.getHits().iterator().next().getName();

        assertEquals("Sklad", name);
    }

}

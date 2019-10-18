package com.tsystems.transportinfo.service;

import com.graphhopper.api.GraphHopperGeocoding;
import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.api.model.GHGeocodingRequest;
import com.graphhopper.api.model.GHGeocodingResponse;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class GraphHopperServiceImpl implements GraphHopperService {

    @Autowired
    private Environment env;

    public GHGeocodingEntry coordsToEntry(String coords) {
        GraphHopperGeocoding graphHopperGeocoding = new GraphHopperGeocoding();
        graphHopperGeocoding.setKey(env.getProperty("api.key"));

        GHPoint point = GHPoint.fromString(coords);
        GHGeocodingRequest request = new GHGeocodingRequest(point, "en", 1);

        GHGeocodingResponse response = graphHopperGeocoding.geocode(request);
        return response.getHits().iterator().next();
    }

}

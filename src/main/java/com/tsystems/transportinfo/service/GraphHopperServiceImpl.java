package com.tsystems.transportinfo.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.api.GraphHopperGeocoding;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.api.model.GHGeocodingRequest;
import com.graphhopper.api.model.GHGeocodingResponse;
import com.graphhopper.util.shapes.GHPoint;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GraphHopperServiceImpl implements GraphHopperService {

    @Autowired
    private Environment env;

    public GHPoint pointFromEntry(GHGeocodingEntry entry) {
        double lat = entry.getPoint().getLat();
        double lng = entry.getPoint().getLng();
        return new GHPoint(lat, lng);
    }

    public GHGeocodingEntry coordsToEntry(String coords) {
        GraphHopperGeocoding graphHopperGeocoding = new GraphHopperGeocoding();
        graphHopperGeocoding.setKey(env.getProperty("api.key"));

        GHPoint point = GHPoint.fromString(coords);
        GHGeocodingRequest request = new GHGeocodingRequest(point, "en", 1);

        GHGeocodingResponse response = graphHopperGeocoding.geocode(request);
        return response.getHits().iterator().next();
    }

    public long timeOfPath(GHPoint startPlace, GHPoint endPlace) {
        GraphHopperWeb gh = new GraphHopperWeb();
        gh.setKey(env.getProperty("api.key"));

        gh.setDownloader(new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).build());

        GHRequest req = new GHRequest(startPlace, endPlace);
        req.setVehicle("truck");

        // Optionally enable/disable turn instruction information, defaults is true
        req.getHints().put("instructions", false);
        // Optionally enable/disable path geometry information, default is true
        req.getHints().put("calc_points", false);

        GHResponse fullRes = gh.route(req);

        PathWrapper res = fullRes.getBest();
        return res.getTime();
    }

}

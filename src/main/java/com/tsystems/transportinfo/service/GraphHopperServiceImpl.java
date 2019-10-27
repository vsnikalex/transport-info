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

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public double distance(double lat1, double lat2, double lon1,
                    double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}

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
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GraphHopperServiceImpl implements GraphHopperService {

    @Autowired
    private Environment env;

    @Override
    public GHPoint pointFromEntry(GHGeocodingEntry entry) {
        double lat = entry.getPoint().getLat();
        double lng = entry.getPoint().getLng();
        log.info("Convert entry with lat={}, lon={} to GHPoint", lat, lng);
        return new GHPoint(lat, lng);
    }

    @Override
    public String pointStringFromEntry(GHGeocodingEntry entry) {
        log.info("Extract coordinates as String from entry");
        GHPoint point = pointFromEntry(entry);
        return point.toString();
    }

    @Override
    public String normalize(String point) {
        String[] coords = point.split(",");
        coords[0] = coords[0].substring(0, coords[0].indexOf(".") + 6);
        coords[1] = coords[1].substring(0, coords[1].indexOf(".") + 6);
        log.info("Normalize point coordinates: {},{}", coords[0], coords[1]);
        return coords[0] + "," + coords[1];
    }

    @Override
    public GHGeocodingEntry coordsToEntry(String coords) {
        log.info("Convert coordinates {} to GHGeocodingEntry using GH client", coords);

        GraphHopperGeocoding graphHopperGeocoding = new GraphHopperGeocoding();
        graphHopperGeocoding.setKey(env.getProperty("api.key"));

        GHPoint point = GHPoint.fromString(coords);
        GHGeocodingRequest request = new GHGeocodingRequest(point, "en", 1);

        GHGeocodingResponse response = graphHopperGeocoding.geocode(request);
        return response.getHits().iterator().next();
    }

    @Override
    public long timeOfPath(GHPoint startPlace, GHPoint endPlace) {
        log.info("Calculate distance considering route between {} and {}", startPlace, endPlace);
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
    @Override
    public double distance(double lat1, double lat2, double lon1,
                    double lon2, double el1, double el2) {
        log.info("Calculate straight distance between {},{} and {},{}", lat1, lon1, lat2, lon2);
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

    @Override
    public boolean inSameCity(GHGeocodingEntry a, GHGeocodingEntry b) {
        log.info("Compare entries");
        return a.getCountry().equals(b.getCountry()) &&
               a.getState().equals(b.getState()) &&
               a.getCity().equals(b.getCity());
    }

}

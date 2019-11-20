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
public class GeoServiceImpl implements GeoService {

    @Autowired
    private Environment env;

    /**
     * @param entry full information about a place
     * @return      coordinates of that place
     */
    @Override
    public GHPoint pointFromEntry(GHGeocodingEntry entry) {
        double lat = entry.getPoint().getLat();
        double lng = entry.getPoint().getLng();
        return new GHPoint(lat, lng);
    }

    /**
     * @param entry full information about a place
     * @return      coordinates of that place as String
     */
    @Override
    public String pointStringFromEntry(GHGeocodingEntry entry) {
        GHPoint point = pointFromEntry(entry);
        return point.toString();
    }

    /**
     * When coordinates received from different sources
     * and used as keys in HashMap,
     * normalization is useful.
     *
     * This method takes coordinates with different accuracy
     * and slices off digits after the fifths one.
     * Right format is "12.3456789,12.3456789", no checks provided!
     *
     * @param point coordinates of a place
     * @return      normalized coordinates
     */
    @Override
    public String normalize(String point) {
        String[] coords = point.split(",");
        coords[0] = coords[0].substring(0, coords[0].indexOf(".") + 6);
        coords[1] = coords[1].substring(0, coords[1].indexOf(".") + 6);
        return coords[0] + "," + coords[1];
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @return Distance in Meters
     */
    @Override
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

    /**
     * Compares country, state and city.
     *
     * @param a    first object
     * @param b    second object
     * @return     if both objects are in the same city
     */
    @Override
    public boolean inSameCity(GHGeocodingEntry a, GHGeocodingEntry b) {
        return a.getCountry().equals(b.getCountry()) &&
               a.getState().equals(b.getState()) &&
               a.getCity().equals(b.getCity());
    }

    /**
     * Takes coordinates and performs
     * request to the GraphHopper API.
     *
     * @deprecated          the result is not found quite frequently
     *
     * @param coords    coordinates of a place
     * @return          full information about the place
     */
    @Override
    @Deprecated
    public GHGeocodingEntry coordsToEntry(String coords) {
        GraphHopperGeocoding graphHopperGeocoding = new GraphHopperGeocoding();
        graphHopperGeocoding.setKey(env.getProperty("api.key"));

        GHPoint point = GHPoint.fromString(coords);
        GHGeocodingRequest request = new GHGeocodingRequest(point, "en", 1);

        GHGeocodingResponse response = graphHopperGeocoding.geocode(request);
        return response.getHits().iterator().next();
    }

    /**
     * Takes two points and requests estimated travel time
     *
     * @deprecated          the result is not found quite frequently
     *
     * @param startPlace    coordinates of route start
     * @param endPlace      coordinates of route finish
     * @return              travel time in milliseconds
     */
    @Override
    @Deprecated
    public long timeOfPath(GHPoint startPlace, GHPoint endPlace) {
        GraphHopperWeb gh = new GraphHopperWeb();
        gh.setKey(env.getProperty("api.key"));

        gh.setDownloader(new OkHttpClient.Builder().
                connectTimeout(5, TimeUnit.SECONDS).
                readTimeout(5, TimeUnit.SECONDS).build());

        GHRequest req = new GHRequest(startPlace, endPlace);
        req.setVehicle("car");

        // Optionally enable/disable turn instruction information, defaults is true
        req.getHints().put("instructions", false);
        // Optionally enable/disable path geometry information, default is true
        req.getHints().put("calc_points", false);

        GHResponse fullRes = gh.route(req);

        PathWrapper res = fullRes.getBest();
        return res.getTime();
    }

}

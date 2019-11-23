package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;

public interface GeoService {

    GHPoint pointFromEntry(GHGeocodingEntry entry);

    String pointStringFromEntry(GHGeocodingEntry entry);

    String format(String point);

    GHGeocodingEntry coordsToEntry(String coords);

    long timeOfPath(GHPoint startPlace, GHPoint endPlace);

    double distance(double lat1, double lat2, double lon1,
                    double lon2, double el1, double el2);

    boolean inSameCity(GHGeocodingEntry a, GHGeocodingEntry b);

}

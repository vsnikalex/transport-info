package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;

public interface GraphHopperService {

    GHPoint pointFromEntry(GHGeocodingEntry entry);

    GHGeocodingEntry coordsToEntry(String coords);

    long timeOfPath(GHPoint startPlace, GHPoint endPlace);

    double distance(double lat1, double lat2, double lon1,
                    double lon2, double el1, double el2);

}

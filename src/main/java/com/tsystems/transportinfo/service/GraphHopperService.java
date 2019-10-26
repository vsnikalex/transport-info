package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;

public interface GraphHopperService {

    GHPoint pointFromEntry(GHGeocodingEntry entry);

    GHGeocodingEntry coordsToEntry(String coords);

    long timeOfPath(GHPoint startPlace, GHPoint endPlace);

}

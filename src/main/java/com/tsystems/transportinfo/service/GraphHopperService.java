package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;

public interface GraphHopperService {

    GHGeocodingEntry coordsToEntry(String coords);

}

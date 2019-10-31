package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverDAO {
    List<Driver> findAvailableDrivers(GHGeocodingEntry city);
}

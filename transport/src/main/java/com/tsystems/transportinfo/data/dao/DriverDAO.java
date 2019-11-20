package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverDAO {

    int calculateDrivers();

    List<Driver> findAvailableDrivers(GHGeocodingEntry city);

    int calculateAvailableDrivers();

    Driver findDriver(long id);

    void updateDriver(Driver driver);

    void assignToDelivery(long driverId, long deliveryID);

}

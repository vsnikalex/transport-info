package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckDAO {
    List<Truck> findAvailableTrucks(GHPoint destination, long maxTravelTime);
}

package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckDAO {

    int calculateTrucks();

    int calculateUsedTrucks();

    int calculateDefectiveTrucks();

    List<Truck> findAvailableTrucks(GHPoint destination, long maxTravelTime);

}

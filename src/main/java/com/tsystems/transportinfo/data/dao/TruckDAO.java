package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckDAO {

    public List<Truck> findAllTrucks();

    public void saveTruck(Truck truck);

    public Truck findTruck(Long id);

    public void deleteTruck(Long id);

}

package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckService {

    public List<Truck> getAllTrucks();

    public void saveTruck(Truck truck);

    public void updateTruck(Truck truck);

    public Truck getTruck(String plate);

    public void deleteTruck(String plate);

}

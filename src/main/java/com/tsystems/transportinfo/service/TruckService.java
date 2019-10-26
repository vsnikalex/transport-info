package com.tsystems.transportinfo.service;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckService {

    List<TruckDTO> getNearestTrucks(GHPoint destination, long maxTravelTime);

    List<TruckDTO> getAllTrucks();

    void saveTruck(TruckDTO truckDTO);

    void updateTruck(TruckDTO truckDTO);

    TruckDTO getTruck(Long id);

    void deleteTruck(Long id);

    TruckDTO convertToDto(Truck entity);

    Truck convertToEntity(TruckDTO dto);

}

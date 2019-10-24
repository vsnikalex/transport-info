package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;

import java.util.List;

public interface TruckService {

    public List<TruckDTO> getAllTrucks();

    public void saveTruck(TruckDTO truckDTO);

    public void updateTruck(TruckDTO truckDTO);

    public TruckDTO getTruck(Long id);

    public void deleteTruck(Long id);

    public TruckDTO convertToDto(Truck entity);

    public Truck convertToEntity(TruckDTO dto);

}

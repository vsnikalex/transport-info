package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;

import java.util.List;

public interface CargoService {

    List<CargoDTO> getAvailableByDepotId(Long id);

    List<CargoDTO> getAllCargoes();

    void saveCargo(CargoDTO cargoDTO);

    void updateCargo(CargoDTO cargoDTO);

    void updateCargoStatus(long id, CargoStatus status);

    CargoDTO getCargo(Long id);

    void deleteCargo(Long id);

    CargoDTO convertToDto(Cargo entity);

    Cargo convertToEntity(CargoDTO dto);

}

package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoService {

    List<CargoDTO> getAvailableByDepotId(Long id);

    List<CargoDTO> getAllCargoes();

    void saveCargo(CargoDTO cargoDTO);

    void updateCargo(CargoDTO cargoDTO);

    CargoDTO getCargo(Long id);

    void deleteCargo(Long id);

    CargoDTO convertToDto(Cargo entity);

    public Cargo convertToEntity(CargoDTO dto);

}

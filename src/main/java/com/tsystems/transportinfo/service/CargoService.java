package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoService {

    public List<CargoDTO> getAllCargoes();

    public void saveCargo(CargoDTO cargoDTO);

    public void updateCargo(CargoDTO cargoDTO);

    public CargoDTO getCargo(Long id);

    public void deleteCargo(Long id);

    public CargoDTO convertToDto(Cargo entity);

    public Cargo convertToEntity(CargoDTO dto);

}

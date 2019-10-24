package com.tsystems.transportinfo.data.converters;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.DepotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepotService depotService;

    public CargoDTO convertToDto(Cargo entity) {
        return modelMapper.map(entity, CargoDTO.class);
    }

    public Cargo convertToEntity(CargoDTO dto) {
        Cargo cargo = modelMapper.map(dto, Cargo.class);

        long start = dto.getLocDepotId();
        cargo.setLocation(depotService.getDepot(start));
        long end = dto.getDestDepotId();
        cargo.setDestination(depotService.getDepot(end));

        return cargo;
    }

}

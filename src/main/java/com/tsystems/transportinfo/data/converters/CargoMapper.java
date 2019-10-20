package com.tsystems.transportinfo.data.converters;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GraphHopperService graphHopperService;

    public CargoDTO convertToDto(Cargo entity) {
        return modelMapper.map(entity, CargoDTO.class);
    }

    public Cargo convertToEntity(CargoDTO dto) {
        Cargo cargo = modelMapper.map(dto, Cargo.class);

        String start = dto.getLocCoords();
        cargo.setLocation(graphHopperService.coordsToEntry(start));
        String end = dto.getDestCoords();
        cargo.setDestination(graphHopperService.coordsToEntry(end));

        return cargo;
    }

}

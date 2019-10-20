package com.tsystems.transportinfo.data.converters;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GraphHopperService graphHopperService;

    public TruckDTO convertToDto(Truck entity) {
        TruckDTO truckDTO = modelMapper.map(entity, TruckDTO.class);

        truckDTO.setDriversCnt(entity.getDelivery());

        return truckDTO;
    }

    public Truck convertToEntity(TruckDTO dto) {
        Truck truck = modelMapper.map(dto, Truck.class);

        String coords = dto.getCoords();
        truck.setLocation(graphHopperService.coordsToEntry(coords));

        return truck;
    }

}

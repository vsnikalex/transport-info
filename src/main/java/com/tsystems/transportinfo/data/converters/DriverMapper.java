package com.tsystems.transportinfo.data.converters;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GraphHopperService graphHopperService;

    public DriverDTO convertToDto(Driver entity) {
        DriverDTO driverDTO = modelMapper.map(entity, DriverDTO.class);

        driverDTO.setWorkedThisMonth(entity.getTasks());
        driverDTO.setStatus(entity.getTasks());
        driverDTO.setTruck(entity.getDelivery());

        return driverDTO;
    }

    public Driver convertToEntity(DriverDTO dto) {
        Driver driver = modelMapper.map(dto, Driver.class);

        String coords = dto.getCoords();
        driver.setLocation(graphHopperService.coordsToEntry(coords));

        return driver;
    }

}

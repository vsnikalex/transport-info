package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.service.TruckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/truck")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<TruckDTO> allTrucks() {
        List<Truck> trucks = truckService.getAllTrucks();
        return trucks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TruckDTO convertToDto(Truck truck) {
        TruckDTO truckDTO = modelMapper.map(truck, TruckDTO.class);

        truckDTO.setDriversCnt(truck.getDelivery());

        return truckDTO;
    }

}

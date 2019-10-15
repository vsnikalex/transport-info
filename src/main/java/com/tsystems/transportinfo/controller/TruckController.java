package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.service.TruckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @DeleteMapping("/delete/{plate}")
    public void deleteTruck(@PathVariable String plate) {
        truckService.deleteTruck(plate);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveCargo(
            @RequestBody @Valid TruckDTO truckDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        Truck truck = this.convertToEntity(truckDTO);
        truckService.saveTruck(truck);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    private TruckDTO convertToDto(Truck truck) {
        TruckDTO truckDTO = modelMapper.map(truck, TruckDTO.class);

        truckDTO.setDriversCnt(truck.getDelivery());

        return truckDTO;
    }

    private Truck convertToEntity(TruckDTO truckDTO) {
        return modelMapper.map(truckDTO, Truck.class);
    }
}

package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.service.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/admin/api/truck")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping("/all/{depotId}/{maxTravelTime}")
    public List<TruckDTO> allAvailableTrucks(@PathVariable long depotId, @PathVariable long maxTravelTime) {
        log.info("Request all Trucks nearby Depot id={}, with max travel time={}(seconds)", depotId, maxTravelTime);
        return truckService.getAvailableTrucks(depotId, maxTravelTime);
    }

    @GetMapping("/all")
    public List<TruckDTO> allTrucks() {
        log.info("Request all Trucks");
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public TruckDTO getTruck(@PathVariable long id) {
        log.info("Request Truck id={}", id);
        return truckService.getTruck(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTruck(@PathVariable long id) {
        log.info("Delete Truck id={}", id);
        truckService.deleteTruck(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveCargo(
            @RequestBody @Valid TruckDTO truckDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        truckService.saveTruck(truckDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCargo(
            @RequestBody @Valid TruckDTO truckDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        truckService.updateTruck(truckDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

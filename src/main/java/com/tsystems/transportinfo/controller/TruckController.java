package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.service.TruckService;
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

    @GetMapping("/all/{depotId}/{maxTravelTime}")
    public List<TruckDTO> allAvailableTrucks(@PathVariable long depotId, @PathVariable long maxTravelTime) {
        return truckService.getAvailableTrucks(depotId, maxTravelTime);
    }

    @GetMapping("/all")
    public List<TruckDTO> allTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public TruckDTO getTruck(@PathVariable long id) {
        return truckService.getTruck(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTruck(@PathVariable long id) {
        truckService.deleteTruck(id);
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

            return ResponseEntity.badRequest().body(msg);
        }

        truckService.updateTruck(truckDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

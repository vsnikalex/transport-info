package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.aspect.DeliveryEvent;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import com.tsystems.transportinfo.service.CargoService;
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
@RequestMapping("/admin/api/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/all/{depotId}")
    public List<CargoDTO> allAvailableCargoesAtDepot(@PathVariable long depotId) {
        log.info("Request available Cargoes at Depot id={}", depotId);
        return cargoService.getAvailableByDepotId(depotId);
    }

    @GetMapping("/all")
    public List<CargoDTO> allCargoes() {
        log.info("Request all Cargoes");
        return cargoService.getAllCargoes();
    }

    @GetMapping("/{id}")
    public CargoDTO getCargo(@PathVariable long id) {
        log.info("Request Cargo with id={}", id);
        return cargoService.getCargo(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCargo(@PathVariable long id) {
        log.info("Delete Cargo with id={}", id);
        cargoService.deleteCargo(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveCargo(
            @RequestBody @Valid CargoDTO cargoDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        cargoService.saveCargo(cargoDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    @DeliveryEvent
    public ResponseEntity<String> updateCargo(
            @RequestBody @Valid CargoDTO cargoDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        cargoService.updateCargo(cargoDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update/status/{id}/{status}")
    public void updateCargoStatus(
            @PathVariable long id, @PathVariable CargoStatus status) {
        log.info("Change Cargo status with id={} to {}", id, status);
        cargoService.updateCargoStatus(id, status);
    }

}

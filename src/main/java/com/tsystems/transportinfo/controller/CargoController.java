package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/all/{depotId}")
    public List<CargoDTO> allCargoes(@PathVariable long depotId) {
        return cargoService.getByDepotId(depotId);
    }

    @GetMapping("/all")
    public List<CargoDTO> allCargoes() {
        return cargoService.getAllCargoes();
    }

    @GetMapping("/{id}")
    public CargoDTO getCargo(@PathVariable long id) {
        return cargoService.getCargo(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCargo(@PathVariable long id) {
        cargoService.deleteCargo(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveCargo(
            @RequestBody @Valid CargoDTO cargoDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        cargoService.saveCargo(cargoDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCargo(
            @RequestBody @Valid CargoDTO cargoDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        cargoService.updateCargo(cargoDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

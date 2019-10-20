package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.converters.CargoMapper;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
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

    @Autowired
    private CargoMapper cargoMapper;

    @GetMapping("/all")
    public List<CargoDTO> allCargoes() {
        List<Cargo> cargoes = cargoService.getAllCargoes();
        return cargoes.stream()
                .map(cargo -> cargoMapper.convertToDto(cargo))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CargoDTO getCargo(@PathVariable long id) {
        Cargo cargo = cargoService.getCargo(id);
        return cargoMapper.convertToDto(cargo);
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

        Cargo cargo = cargoMapper.convertToEntity(cargoDTO);
        cargoService.saveCargo(cargo);

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

        Cargo cargo = cargoMapper.convertToEntity(cargoDTO);
        cargoService.updateCargo(cargo);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

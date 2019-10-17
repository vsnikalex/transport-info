package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.CargoService;
import de.westnordost.osmapi.map.data.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<CargoDTO> allCargoes() {
        List<Cargo> cargoes = cargoService.getAllCargoes();
        return cargoes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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

        Cargo cargo = this.convertToEntity(cargoDTO);
        cargoService.saveCargo(cargo);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    private CargoDTO convertToDto(Cargo cargo) {
        return modelMapper.map(cargo, CargoDTO.class);
    }

    private Cargo convertToEntity(CargoDTO cargoDTO) {
        Cargo cargo = modelMapper.map(cargoDTO, Cargo.class);

        return cargo;
    }

}

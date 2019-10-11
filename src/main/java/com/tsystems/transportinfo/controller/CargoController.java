package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.CargoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/api/cargo/all")
    public List<CargoDTO> allCargoes() {
        List<Cargo> cargoes = cargoService.getAllCargoes();
        return cargoes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CargoDTO convertToDto(Cargo cargo) {
        return modelMapper.map(cargo, CargoDTO.class);
    }

}

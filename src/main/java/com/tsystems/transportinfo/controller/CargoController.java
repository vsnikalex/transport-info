package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.CargoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/api/cargo/all")
    public List<Cargo> allCargoes() {
        return cargoService.getAllCargoes();
    }

}

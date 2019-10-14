package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.service.CargoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    public String saveCargo(@RequestBody CargoDTO cargoDTO) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        if (!validator.validate(cargoDTO).isEmpty()) {
            return "FAIL";
        }

        return "SUCCESS";
    }

    private CargoDTO convertToDto(Cargo cargo) {
        return modelMapper.map(cargo, CargoDTO.class);
    }

}

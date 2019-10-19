package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.DriverService;
import com.tsystems.transportinfo.service.GraphHopperService;
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
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private GraphHopperService graphHopperService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<DriverDTO> allDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable long id) {
        Driver driver = driverService.getDriver(id);
        return convertToDto(driver);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDriver(@PathVariable long id) {
        driverService.deleteDriver(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveDriver(
            @RequestBody @Valid DriverDTO driverDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        Driver driver = this.convertToEntity(driverDTO);
        driverService.saveDriver(driver);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCargo(
            @RequestBody @Valid DriverDTO driverDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        Driver driver = this.convertToEntity(driverDTO);
        driverService.updateDriver(driver);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    private DriverDTO convertToDto(Driver driver) {
        DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);

        driverDTO.setWorkedThisMonth(driver.getTasks());
        driverDTO.setStatus(driver.getTasks());
        driverDTO.setTruck(driver.getTasks());

        return driverDTO;
    }

    private Driver convertToEntity(DriverDTO driverDTO) {
        Driver driver = modelMapper.map(driverDTO, Driver.class);

        String coords = driverDTO.getCoords();
        driver.setLocation(graphHopperService.coordsToEntry(coords));

        return driver;
    }

}

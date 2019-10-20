package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.converters.DriverMapper;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.DriverService;
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
    private DriverMapper driverMapper;

    @GetMapping("/all")
    public List<DriverDTO> allDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return drivers.stream()
                .map(driver -> driverMapper.convertToDto(driver))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable long id) {
        Driver driver = driverService.getDriver(id);
        return driverMapper.convertToDto(driver);
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

        Driver driver = driverMapper.convertToEntity(driverDTO);
        driverService.saveDriver(driver);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDriver(
            @RequestBody @Valid DriverDTO driverDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.badRequest().body(msg);
        }

        Driver driver = driverMapper.convertToEntity(driverDTO);
        driverService.updateDriver(driver);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

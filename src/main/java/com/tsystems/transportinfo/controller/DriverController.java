package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<DriverDTO> allDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return drivers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDriver(@PathVariable long id) {
        driverService.deleteDriver(id);
    }

    private DriverDTO convertToDto(Driver driver) {
        DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);

        driverDTO.setWorkedThisMonth(driver.getTasks());
        driverDTO.setStatus(driver.getTasks());
        driverDTO.setTruck(driver.getTasks());

        return driverDTO;
    }

}

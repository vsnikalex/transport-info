package com.tsystems.transportinfo.controller;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.service.DriverService;
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
@RequestMapping("/admin/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/city")
    public List<DriverDTO> allAvailableDrivers(@RequestBody GHGeocodingEntry city) {
        log.info("Request all Drivers in {}", city.getCity());
        return driverService.getAvailableDrivers(city);
    }

    @GetMapping("/all")
    public List<DriverDTO> allDrivers() {
        log.info("Request all Drivers");
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable long id) {
        log.info("Request Driver id={}", id);
        return driverService.getDriver(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDriver(@PathVariable long id) {
        log.info("Delete Driver id={}", id);
        driverService.deleteDriver(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveDriver(
            @RequestBody @Valid DriverDTO driverDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        driverService.saveDriver(driverDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDriver(
            @RequestBody @Valid DriverDTO driverDTO, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            log.info("DTO is not valid");
            return ResponseEntity.badRequest().body(msg);
        }

        log.info("DTO is valid");
        driverService.updateDriver(driverDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

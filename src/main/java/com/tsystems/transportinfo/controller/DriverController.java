package com.tsystems.transportinfo.controller;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dto.DriverDTO;
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

    @GetMapping("/city")
    public List<DriverDTO> driversByCity(@RequestBody GHGeocodingEntry city) {
        return driverService.getDriversByCity(city);
    }

    @GetMapping("/all")
    public List<DriverDTO> allDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public DriverDTO getDriver(@PathVariable long id) {
        return driverService.getDriver(id);
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

            return ResponseEntity.badRequest().body(msg);
        }

        driverService.updateDriver(driverDTO);

        return ResponseEntity.ok().body("{\"msg\":\"SAVED\"}");
    }

}

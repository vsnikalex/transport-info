package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import com.tsystems.transportinfo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/driver/api")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DepotService depotService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CargoService cargoService;

    @GetMapping("/mydata/{id}")
    public DriverDTO getDriver(@PathVariable long id) {
        log.info("Request Driver id={}", id);
        return driverService.getDriver(id);
    }

    @GetMapping("/depot/{coords}/")
    public Depot getDepotInfo(@PathVariable String coords) {
        log.info("Request Depot by coordinates={}", coords);
        return depotService.getDepotByCoords(coords);
    }

    @GetMapping("/truck/{id}")
    public TruckDTO getTruckInfo(@PathVariable long id) {
        log.info("Request Truck id={}", id);
        return truckService.getTruck(id);
    }

    @GetMapping("/task/start/{action}/{time}/{driverId}/{truckId}")
    public double startTask(
            @PathVariable DriverAction action, @PathVariable long time,
            @PathVariable long driverId, @PathVariable long truckId) {

        log.info("For Driver id={} log task {} start, using Truck={} at {} (Unix timestamp)", driverId, action, truckId, time);
        return taskService.startTask(action, time, driverId, truckId);
    }

    @GetMapping("/task/finish/{driverId}/{time}")
    public double finishTask(
            @PathVariable long driverId, @PathVariable long time) {

        log.info("For Driver id={} log current task finish at {} (Unix timestamp)", driverId, time);
        return taskService.finishCurrentTask(driverId, time);
    }

    @PutMapping("/cargo/update/status/{id}/{status}")
    public void updateCargoStatus(
            @PathVariable long id, @PathVariable CargoStatus status) {
        log.info("Change Cargo status with id={} to {}", id, status);
        cargoService.updateCargoStatus(id, status);
    }

}

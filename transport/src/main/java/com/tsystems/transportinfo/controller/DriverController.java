package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.dto.DriverDTO;
import com.tsystems.transportinfo.data.dto.TruckDTO;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.service.DepotService;
import com.tsystems.transportinfo.service.DriverService;
import com.tsystems.transportinfo.service.TruckService;
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

}

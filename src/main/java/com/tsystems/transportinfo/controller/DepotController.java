package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/depot")
public class DepotController {

    @Autowired
    private DepotService depotService;

    @GetMapping("/all")
    public List<Depot> allDepots() {
        return depotService.getAllDepots();
    }

    @GetMapping("/{coords}/")
    public Depot depotByCoords(@PathVariable String coords) {
        return depotService.getDepotByCoords(coords);
    }

}

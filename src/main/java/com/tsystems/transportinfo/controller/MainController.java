package com.tsystems.transportinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/admin_truck";
    }

    @GetMapping("/admin_truck")
    public String adminTruck() {
        return "admin_truck";
    }

    @GetMapping("/admin_truck_add")
    public String adminTruckAdd() {
        return "admin_truck_add";
    }

    @GetMapping("/admin_driver")
    public String adminDriver() {
        return "admin_driver";
    }

    @GetMapping("/admin_driver_add")
    public String adminDriverAdd() {
        return "admin_driver_add";
    }

    @GetMapping("/admin_cargo")
    public String adminCargo() {
        return "admin_cargo";
    }

    @GetMapping("/admin_cargo_add")
    public String adminCargoAdd() {
        return "admin_cargo_add";
    }

    @GetMapping("/admin_order")
    public String adminOrder() {
        return "admin_order";
    }

}

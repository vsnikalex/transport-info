package com.tsystems.transportinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "admin_cargo";
    }

    @GetMapping("/admin_cargo")
    public String adminCargo() {
        return "admin_cargo";
    }

    @GetMapping("/admin_driver")
    public String adminDriver() {
        return "admin_driver";
    }

}

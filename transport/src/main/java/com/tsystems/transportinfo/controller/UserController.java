package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/whoami")
    @ResponseBody
    public long whoami(Authentication authentication) {
        String username = authentication.getName();
        return -1;
    }

}

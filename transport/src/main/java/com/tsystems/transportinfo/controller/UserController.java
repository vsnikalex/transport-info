package com.tsystems.transportinfo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/whoami")
    @ResponseBody
    public String whoami(Authentication authentication) {
        return authentication.getName();
    }

}

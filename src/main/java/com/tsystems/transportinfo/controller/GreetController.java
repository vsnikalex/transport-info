package com.tsystems.transportinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GreetController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hello", "Hello, World!");

        return "index";
    }

}


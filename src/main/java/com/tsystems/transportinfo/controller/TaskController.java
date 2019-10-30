package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/hours/{driverId}/{date}")
    public double futureWorkedHours(
            @PathVariable long driverId, @PathVariable long date) {

        return taskService.getFutureWorkedHours(driverId, date);
    }

}

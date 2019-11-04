package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import com.tsystems.transportinfo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/hours/{driverId}/{date}")
    public double futureWorkedHours(
            @PathVariable long driverId, @PathVariable long date) {

        log.info("Request hours that will be worked by Driver id={} by date={} (Unix timestamp)", driverId, date);
        return taskService.getFutureWorkedHours(driverId, date);
    }

    @GetMapping("/start/{action}/{time}/{driverId}/{truckId}")
    public double startTask(
            @PathVariable DriverAction action, @PathVariable long time,
            @PathVariable long driverId, @PathVariable long truckId) {

        log.info("For Driver id={} log task {} start, using Truck={} at {} (Unix timestamp)", driverId, action, truckId, time);
        return taskService.startTask(action, time, driverId, truckId);
    }

    @GetMapping("/finish/{driverId}/{time}")
    public double finishTask(
            @PathVariable long driverId, @PathVariable long time) {

        log.info("For Driver id={} log current task finish at {} (Unix timestamp)", driverId, time);
        return taskService.finishCurrentTask(driverId, time);
    }

}

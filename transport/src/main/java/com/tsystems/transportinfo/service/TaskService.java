package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;

import java.util.List;

public interface TaskService {

    double getFutureWorkedHours(Long driverId, long date);

    double startTask(DriverAction action, long startTime, Long driverId, Long truckId);

    double finishCurrentTask(Long driverId, long endTime);

    double calculateWorkHours(List<Task> tasks, long date);

}

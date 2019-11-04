package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskDAO {

    List<Task> findTasksByDriverId(Long id);

    void startTask(DriverAction action, LocalDateTime time, Long driverId, Long truckId);

    void finishCurrentTask(Long driverId, LocalDateTime end);

}

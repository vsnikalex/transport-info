package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.TaskDAO;
import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public double startTask(DriverAction action, long startTime, Long driverId, Long truckId) {
        log.info("Request TaskDAO to log start of Driver id={} action {} at {} (Unix timestamp) with Truck id={}",
                    driverId, action, startTime, truckId);
        LocalDateTime start = LocalDateTime.ofEpochSecond(startTime, 0, ZoneOffset.UTC);
        taskDAO.startTask(action, start, driverId, truckId);
        return start.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    @Transactional
    public double finishCurrentTask(Long driverId, long endTime) {
        log.info("For Driver id={} log current task finish at {} (Unix timestamp)", driverId, endTime);
        LocalDateTime end = LocalDateTime.ofEpochSecond(endTime, 0, ZoneOffset.UTC);
        taskDAO.finishCurrentTask(driverId, end);
        return end.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    @Transactional
    public double getFutureWorkedHours(Long driverId, long date) {
        log.info("Request Driver id={} tasks for date = {} (Unix timestamp)", driverId, date);
        List<Task> driverTasks = taskDAO.findTasksByDriverId(driverId);

        return calculateWorkHours(driverTasks, date);
    }

    public double calculateWorkHours(List<Task> tasks, long date) {
        LocalDateTime endOfMonth = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC);
        LocalDateTime monthBeforeIt = endOfMonth.minus(1, ChronoUnit.MONTHS);

        log.info("Calculate working hours from {} to {}", monthBeforeIt, endOfMonth);

        Stream<Task> lastMonth = tasks.stream().filter(t ->  t.getStart().isBefore(endOfMonth) &&
                (t.getEnd() == null || t.getEnd().isAfter(monthBeforeIt)));

        Stream<Task> leftChunked = lastMonth.map(t -> t.getStart().isBefore(monthBeforeIt) ? t.withStart(monthBeforeIt) : t);

        Stream<Task> rightChunked = leftChunked.map(t -> t.getEnd() == null || t.getEnd().isAfter(endOfMonth) ? t.withEnd(endOfMonth) : t);

        long worked = rightChunked.map(t -> Duration.between(t.getStart(), t.getEnd()).toMinutes())
                .reduce(0L, Long::sum);

        double precise = ((double) worked / 60);
        double rounded = (double) Math.round(precise * 10) / 10;

        return rounded;
    }

}

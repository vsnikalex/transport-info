package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.TaskDAO;
import com.tsystems.transportinfo.data.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public double getFutureWorkedHours(Long driverId, long date) {
        List<Task> driverTasks = taskDAO.findTasksByDriverId(driverId);

        return calculateWorkHours(driverTasks, date);
    }

    private double calculateWorkHours(List<Task> tasks, long date) {
        LocalDateTime endOfMonth = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC);
        LocalDateTime monthBeforeIt = endOfMonth.minus(1, ChronoUnit.MONTHS);

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

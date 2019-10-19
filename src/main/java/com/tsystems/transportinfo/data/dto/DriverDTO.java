package com.tsystems.transportinfo.data.dto;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverDTO {

    private Long id;

    @NotEmpty(message = "First Name must not be empty")
    @Size(max = 10, message = "Fist Name is too long")
    private String firstName;

    @NotEmpty(message = "Last Name must not be empty")
    @Size(max = 10, message = "Last Name is too long")
    private String lastName;

    @NotEmpty(message = "Location must not be empty")
    private String coords;

    @Min(value = 0)
    private double workedThisMonth;

    private DriverAction action;

    @Valid
    private TruckDTO truckDTO;

    private GHGeocodingEntry location;

    public void setWorkedThisMonth(List<Task> tasks) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime monthAgo = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);

        Stream<Task> lastMonth = tasks.stream().filter(t ->  t.getStart().isBefore(now) &&
                                                            (t.getEnd() == null || t.getEnd().isAfter(monthAgo)));

        Stream<Task> leftChunked = lastMonth.map(t -> t.getStart().isBefore(monthAgo) ? t.withStart(monthAgo) : t);

        Stream<Task> rightChunked = leftChunked.map(t -> t.getEnd() == null || t.getEnd().isAfter(now) ? t.withEnd(now) : t);

        long worked = rightChunked.map(t -> Duration.between(t.getStart(), t.getEnd()).toMinutes())
                                  .reduce(0L, Long::sum);

        double precise = ((double) worked / 60);
        double rounded = (double) Math.round(precise * 10) / 10;

        this.workedThisMonth = rounded;
    }

    public void setStatus(List<Task> tasks) {
        // TODO: set DriverAction if there is an unfinished task or return REST
        this.action = DriverAction.REST;
    }

    public void setTruck(List<Task> tasks) {
        // TODO: most recent task -> Truck
        TruckDTO mockedTruck = new TruckDTO();
        mockedTruck.setPlate("AB12345");
        this.truckDTO = mockedTruck;
    }

}

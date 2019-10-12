package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DriverDTO {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String location;

    @Min(value = 0)
    private int workedThisMonth;

    @NotNull
    private DriverAction action;

    @Valid
    private TruckDTO truckDTO;

    public void setWorkedThisMonth(List<Task> tasks) {
        // TODO: calculate working hours based on start-end LocalDateTime
        this.workedThisMonth = 126;
    }

    public void setStatus(List<Task> tasks) {
        // TODO: set DriverAction if there is an unfinished task or REST
        this.action = DriverAction.REST;
    }

    public void setTruck(List<Task> tasks) {
        // TODO: return TruckDTO
        //  most recent Task -> unfinished Delivery -> Truck
        this.truckDTO = null;
    }

}

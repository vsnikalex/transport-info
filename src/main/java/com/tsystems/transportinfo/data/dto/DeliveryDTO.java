package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private Long id;

    @NotNull
    private boolean done;

    @NotNull
    private String route;

    @NotNull
    @Min(value = 0)
    private int workingDrivers;

    @Valid
    private CargoDTO cargo;

    @Valid
    private TruckDTO truck;

    @Valid
    private List<DriverDTO> driverDTOList;

    public void setWorkingDrivers(List<Task> tasks) {
        // TODO: calculate unfinished tasks
        this.workingDrivers = 2;
    }

}

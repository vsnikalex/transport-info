package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private Long id;

    @NotNull
    private boolean done;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String startPoint;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String destination;

    @NotNull
    @Min(value = 0)
    private int workingDrivers;

    @Valid
    private CargoDTO cargoDTO;

    @Valid
    private TruckDTO truckDTO;

    @Valid
    private List<DriverDTO> driverDTOList;

    public void setWorkingDrivers(List<Task> tasks) {
        // TODO: calculate unfinished tasks
        this.workingDrivers = 2;
    }

}

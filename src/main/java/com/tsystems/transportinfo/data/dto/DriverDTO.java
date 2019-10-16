package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import de.westnordost.osmapi.map.data.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

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
    @Size(max = 50, message = "Location name is too long")
    private String locationName;

    @Min(value = 0)
    private int workedThisMonth;

    private DriverAction action;

    @Valid
    private TruckDTO truckDTO;

    public void setLocationName(Node location) {
        // TODO: extract location name
        this.locationName = location.getTags().get("name");
    }

    public void setWorkedThisMonth(List<Task> tasks) {
        // TODO: calculate working hours based on start-end LocalDateTime
        this.workedThisMonth = 126;
    }

    public void setStatus(List<Task> tasks) {
        // TODO: set DriverAction if there is an unfinished task or return REST
        this.action = DriverAction.REST;
    }

    public void setTruck(List<Task> tasks) {
        // TODO: return TruckDTO by Delivery if present
        TruckDTO mockedTruck = new TruckDTO();
        mockedTruck.setPlate("AB12345");
        this.truckDTO = mockedTruck;
    }

}

package com.tsystems.transportinfo.data.dto;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Task;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers allowed for username")
    @NotEmpty(message = "Username must not be empty")
    @Size(max = 10, message = "Username is too long")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers allowed for password")
    @NotEmpty(message = "Password must not be empty")
    @Size(max = 10, message = "Password is too long")
    private String password;

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

    private TruckDTO truckDTO;

    private DeliveryDTO deliveryDTO;

    private GHGeocodingEntry location;

    public void setStatus(List<Task> tasks) {
        DriverAction currentAction = null;

        int n = tasks.size();
        if (n != 0) {
            Task last = tasks.get(n-1);
            if (last.getEnd() == null) {
                currentAction = last.getAction();
            }
        }

        this.action = (currentAction == null) ? DriverAction.REST : currentAction;
    }

}

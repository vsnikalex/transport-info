package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Task;
import de.westnordost.osmapi.map.data.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private Long id;

    @NotNull
    private boolean done;

    // TODO: replace with Nodes to use it in maps and routes
    @NotNull
    private List<String> route;

    @NotNull
    @Min(value = 0)
    private int workingDrivers;

    @Valid
    private CargoDTO cargoDTO;

    @Valid
    private TruckDTO truckDTO;

    @Valid
    private List<DriverDTO> driverDTOList;

    public void setLocationName(List<Node> route) {
        // TODO: extract route
        route.forEach(r -> this.route.add(r.getTags().get("name")));
    }

    public void setWorkingDrivers(List<Task> tasks) {
        // TODO: calculate unfinished tasks
        this.workingDrivers = 2;
    }

}

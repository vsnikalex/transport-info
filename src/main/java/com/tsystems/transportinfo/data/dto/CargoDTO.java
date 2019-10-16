package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import de.westnordost.osmapi.map.data.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CargoDTO {

    private Long id;

    @NotEmpty(message = "Description must not be empty")
    @Size(max = 250, message = "Description is too long")
    private String description;

    @NotNull(message = "Weight must not be null")
    @Min(value = 100, message = "Weight must be between 100 and 27000")
    @Max(value = 27_000, message = "Weight must be between 100 and 27000")
    private int weight;

    @NotNull(message = "Status must not be null")
    private CargoStatus status;

    // TODO: replace with Node to use it in maps and routes
    @NotEmpty(message = "Location must not be empty")
    @Size(max = 50, message = "Location name is too long")
    private String locationName;

    public void setLocationName(Node location) {
        // TODO: extract location name
        this.locationName = location.getTags().get("name");
    }

    CargoDTO(String locationName, String description, int weight, CargoStatus status) {
        this.locationName = locationName;
        this.description = description;
        this.weight = weight;
        this.status = status;
    }

}

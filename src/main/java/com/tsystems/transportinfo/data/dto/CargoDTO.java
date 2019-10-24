package com.tsystems.transportinfo.data.dto;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.CargoMapper;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
@NoArgsConstructor
@Getter
@Setter
public class CargoDTO {

    @Autowired
    private CargoMapper cargoMapper;

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

    @NotNull(message = "Start Location must not be null")
    private long locDepotId;

    @NotNull(message = "End Location must not be null")
    private long destDepotId;

    private GHGeocodingEntry location;
    private GHGeocodingEntry destination;

    // For validation test
    CargoDTO(String description, int weight, CargoStatus status, long locDepotId, long destDepotId) {
        this.description = description;
        this.weight = weight;
        this.status = status;
        this.locDepotId = locDepotId;
        this.destDepotId = destDepotId;
    }

}

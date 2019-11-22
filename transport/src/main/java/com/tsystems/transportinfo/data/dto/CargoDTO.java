package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @NotNull(message = "Start Location must not be null")
    @Min(value = 1, message = "Start Location must be chosen")
    private long startDepotId;

    @NotNull(message = "End Location must not be null")
    @Min(value = 1, message = "End Location must be chosen")
    private long endDepotId;

    private Depot startDepot;
    private Depot endDepot;

}

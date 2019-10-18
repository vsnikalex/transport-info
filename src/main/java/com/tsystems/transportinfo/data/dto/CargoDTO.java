package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
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

    @NotEmpty(message = "Start Location must not be empty")
    private String locCoords;

    @NotEmpty(message = "End Location must not be empty")
    private String destCoords;

}

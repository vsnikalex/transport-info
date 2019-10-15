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

    @NotEmpty(message = "Location must not be empty")
    @Size(max = 50, message = "Location name is too long")
    private String location;

    @NotNull(message = "Weight must not be empty")
    @Min(value = 100, message = "Cargo is too lightweight")
    @Max(value = 27_000, message = "It is an overweight")
    private int weight;

    @NotNull(message = "Status must not be empty")
    private CargoStatus status;

    public CargoDTO(String location, String description, int weight, CargoStatus status) {
        this.location = location;
        this.description = description;
        this.weight = weight;
        this.status = status;
    }

}

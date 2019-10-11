package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CargoDTO {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 250)
    private String description;

    @NotNull
    @Range(min = 100, max = 27_000)
    private int weight;

    @NotNull
    private CargoStatus status;

    CargoDTO(String description, int weight, CargoStatus status) {
        this.description = description;
        this.weight = weight;
        this.status = status;
    }

}

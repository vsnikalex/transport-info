package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CargoDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 250)
    private String description;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String location;

    @NotNull
    @Range(min = 100, max = 27_000)
    private int weight;

    @NotNull
    private CargoStatus status;

    public CargoDTO(String location, String description, int weight, CargoStatus status) {
        this.location = location;
        this.description = description;
        this.weight = weight;
        this.status = status;
    }

}

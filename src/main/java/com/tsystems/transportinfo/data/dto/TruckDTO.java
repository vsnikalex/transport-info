package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class TruckDTO {

    @NotNull
    @Pattern(regexp = "[A-Z]{2}\\d{5}")
    private String plate;

    @NotNull
    @Min(value = 0)
    private int capacity;

    @NotNull
    private TruckStatus status;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String location;

    @Min(value = 0)
    private int driversCnt;

    public void setDriversCnt(Delivery delivery) {
        // TODO: unfinished delivery -> unfinished tasks (working drivers)
        this.driversCnt = 2;
    }

}

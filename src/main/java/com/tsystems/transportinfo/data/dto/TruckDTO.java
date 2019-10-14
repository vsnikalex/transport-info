package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
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
    @NotEmpty
    @Size(max = 10)
    private String location;

    @Min(value = 0)
    private int driversCnt;

    public void setDriversCnt(Delivery delivery) {
        // TODO: unfinished delivery -> unfinished tasks (working drivers)
        this.driversCnt = 2;
    }

}

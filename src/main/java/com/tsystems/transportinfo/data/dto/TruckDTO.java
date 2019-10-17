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

    @NotEmpty(message = "Plate must not be empty")
    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Plate pattern is XY12345")
    private String plate;

    @NotNull(message = "Capacity must not be null")
    @Min(value = 0, message = "Capacity must be greater than zero")
    private int capacity;

    @NotNull(message = "Status must not be null")
    private TruckStatus status;

    @NotEmpty(message = "Location must not be empty")
    @Size(max = 50, message = "Location name is too long")
    private String wayID;

    @Min(value = 0)
    private int driversCnt;

    public void setDriversCnt(Delivery delivery) {
        // TODO: Delivery -> unfinished Tasks (working drivers)
        this.driversCnt = 2;
    }

}

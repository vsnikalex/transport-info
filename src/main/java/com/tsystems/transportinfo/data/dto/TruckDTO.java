package com.tsystems.transportinfo.data.dto;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.converters.TruckMapper;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TruckDTO {

    @Autowired
    private TruckMapper truckMapper;

    private Long id;

    @NotEmpty(message = "Plate must not be empty")
    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Plate pattern is XY12345")
    private String plate;

    @NotNull(message = "Capacity must not be null")
    @Min(value = 0, message = "Capacity must be greater than zero")
    private int capacity;

    @NotNull(message = "Status must not be null")
    private TruckStatus status;

    @NotEmpty(message = "Location must not be empty")
    private String coords;

    @Min(value = 0)
    private int driversCnt;

    private GHGeocodingEntry location;

    public void setDriversCnt() {
        // TODO: Delivery -> unfinished Tasks (working drivers)
        this.driversCnt = 2;
    }

}

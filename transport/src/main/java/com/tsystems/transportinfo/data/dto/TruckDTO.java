package com.tsystems.transportinfo.data.dto;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckDTO {

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

    // TODO: change to DTO / SecuredDTO
    private List<String> driversInfo;

    private GHGeocodingEntry location;

    public void setDriversCnt(Delivery delivery) {
        if (delivery != null && delivery.getDrivers() != null) {
            this.driversCnt = delivery.getDrivers().size();
            this.driversInfo = delivery.getDrivers().stream()
                                                .map(d -> d.getId() + " " + d.getFirstName() + " " + d.getLastName())
                                                .collect(Collectors.toList());
        }
    }
}

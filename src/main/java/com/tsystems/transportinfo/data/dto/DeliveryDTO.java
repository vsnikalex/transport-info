package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.Cargo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    @NotNull
    private Long id;

    @NotNull
    private boolean done;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String startPoint;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 10)
    private String destination;

    @Valid
    private TruckDTO truckDTO;

    @Valid
    private List<DriverDTO> driverDTOList;

    public void setStartPoint(Cargo cargo) {
        // TODO: set cargo's location
        this.startPoint = cargo.getLocation();
    }

}

package com.tsystems.transportinfo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private long[] cargoIDs;

    private long truckID;

    private long[] driverIDs;

    private String route;

}

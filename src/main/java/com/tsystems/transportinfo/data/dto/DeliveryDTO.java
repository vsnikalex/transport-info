package com.tsystems.transportinfo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    @Getter
    @Setter
    public static class CargoOperations {
        private List<CargoDTO> loadOps;
        private List<CargoDTO> unloadOps;

        public CargoOperations() {
            this.loadOps = new LinkedList<>();
            this.unloadOps = new LinkedList<>();
        }

        public void addLoadOp(CargoDTO cargoDTO) {
            loadOps.add(cargoDTO);
        }

        public void addUnloadOp(CargoDTO cargoDTO){
            unloadOps.add(cargoDTO);
        }
    }

    private Long id;

    private long[] cargoIDs;

    private long truckID;

    private long[] driverIDs;

    private String route;

    private Map<String, CargoOperations> routeWithCargoOperations;

}

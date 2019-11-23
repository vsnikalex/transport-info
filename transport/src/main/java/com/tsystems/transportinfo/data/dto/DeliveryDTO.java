package com.tsystems.transportinfo.data.dto;

import lombok.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

        public void addLoadOps(CargoDTO... cargoDTOs) {
            loadOps.addAll(Arrays.asList(cargoDTOs));
        }

        public void addUnloadOps(CargoDTO... cargoDTOs){
            loadOps.addAll(Arrays.asList(cargoDTOs));
        }
    }

    private Long id;

    private long[] cargoIDs;

    private long truckID;

    private long[] driverIDs;

    private String route;

    private Map<String, CargoOperations> routeWithCargoOperations;

}

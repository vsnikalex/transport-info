package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.aspect.DeliveryEvent;
import com.tsystems.transportinfo.aspect.DriverEvent;
import com.tsystems.transportinfo.aspect.TruckEvent;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.dto.CargoDTO;
import com.tsystems.transportinfo.data.dto.DeliveryDTO;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Truck;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private GenericDAO<Delivery> dao;

    @Autowired
    public void setDao(GenericDAO<Delivery> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Delivery.class);
    }

    @Autowired
    private CargoDAO cargoDAO;

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    private GeoService geoService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Uses {@link com.tsystems.transportinfo.data.dao.HibernateDAO},
     * {@link CargoDAO}, {@link DriverDAO} to change relevant
     * database links to the delivery.
     *
     */
    @Override
    @DeliveryEvent
    @DriverEvent
    @TruckEvent
    public void createDelivery(DeliveryDTO deliveryDTO) {
        log.info("Request DAO to save delivery with cargo id={}, driver id={}, truck id={}",
                deliveryDTO.getCargoIDs(), deliveryDTO.getDriverIDs(), deliveryDTO.getTruckID());

        Truck truck = new Truck(deliveryDTO.getTruckID());

        Delivery delivery = new Delivery();
        delivery.setDone(false);
        delivery.setRoute(deliveryDTO.getRoute());
        delivery.setTruck(truck);
        delivery.setCreated(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));

        dao.create(delivery);

        for (long cargoID : deliveryDTO.getCargoIDs()) {
            cargoDAO.assignToDelivery(cargoID, delivery.getId());
        }

        for (long driverId : deliveryDTO.getDriverIDs()) {
            driverDAO.assignToDelivery(driverId, delivery.getId());
        }
    }

    /**
     * TODO: Marks delivery as deleted.
     *
     */
    @Override
    public void deleteDelivery(Long id) {
        log.info("Request DAO to delete Delivery id={}", id);
        dao.deleteById(id);
    }

    @Override
    public Delivery convertToEntity(DeliveryDTO dto) {
        return modelMapper.map(dto, Delivery.class);
    }

    /**
     * Transforms the list of separate cargo routes
     *
     * e.g. cargo #1 : A-B
     *      cargo #2 : A-C
     *      cargo #3 : A-D
     *
     * into the list of operations at each point:
     *
     * A : load cargo #1, #2, #3
     * B : unload cargo #1
     * C : unload cargo #2
     * D : unload cargo #3
     *
     * It is convenient to have data in this format
     * at front-end for further rendering.
     *
     */
    @Override
    public DeliveryDTO convertToDTO(Delivery entity) {
        Map<String, DeliveryDTO.CargoOperations> routeMap = new LinkedHashMap<>();

        String route = entity.getRoute();
        JSONObject jsonObject = new JSONObject(route);

        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String point = (String) jsonObject.get(iterator.next());
            String pointNorm = geoService.normalize(point);
            routeMap.putIfAbsent(pointNorm, new DeliveryDTO.CargoOperations());
        }

        List<Cargo> cargoes = entity.getCargo();
        cargoes.forEach(cargo -> {
            GHGeocodingEntry startEntry = cargo.getStartDepot().getLocation();
            String startPoint = geoService.pointStringFromEntry(startEntry);
            String startPointNorm = geoService.normalize(startPoint);

            GHGeocodingEntry endEntry = cargo.getEndDepot().getLocation();
            String endPoint = geoService.pointStringFromEntry(endEntry);
            String endPointNorm = geoService.normalize(endPoint);

            CargoDTO cargoDTO = modelMapper.map(cargo, CargoDTO.class);

            routeMap.get(startPointNorm).addLoadOp(cargoDTO);
            routeMap.get(endPointNorm).addUnloadOp(cargoDTO);
        });

        DeliveryDTO deliveryDTO = modelMapper.map(entity, DeliveryDTO.class);
        deliveryDTO.setRouteWithCargoOperations(routeMap);

        return deliveryDTO;
    }

}

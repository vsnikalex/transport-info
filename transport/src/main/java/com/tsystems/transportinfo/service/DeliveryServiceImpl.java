package com.tsystems.transportinfo.service;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.dao.DeliveryDAO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private GenericDAO<Delivery> dao;

    @Autowired
    public void setDao(GenericDAO<Delivery> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Delivery.class);
    }

    @Autowired
    private DeliveryDAO deliveryDAO;

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
    @Transactional
    public void createDelivery(DeliveryDTO deliveryDTO) {
        log.info("Request DAO to save delivery with cargo id={}, driver id={}, truck id={}",
                deliveryDTO.getCargoIDs(), deliveryDTO.getDriverIDs(), deliveryDTO.getTruckID());

        Delivery delivery = Delivery.builder()
                .estWorkHours(deliveryDTO.getEstWorkHours())
                .done(false)
                .route(deliveryDTO.getRoute())
                .truck(new Truck(deliveryDTO.getTruckID()))
                .created(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")))
                .build();

        dao.create(delivery);

        for (long cargoID : deliveryDTO.getCargoIDs()) {
            cargoDAO.assignToDelivery(cargoID, delivery.getId());
        }

        for (long driverId : deliveryDTO.getDriverIDs()) {
            driverDAO.assignToDelivery(driverId, delivery.getId());
        }
    }

    @Override
    @Transactional
    public void deleteDelivery(Long id) {
        log.info("Request DAO to delete Delivery id={}", id);
        dao.deleteById(id);
    }

    /**
     * Underlying DAO checks that there is no
     * undelivered cargoes and marks delivery as done
     * if it is true, or does nothing.
     *
     * @return false if delivery remained unchanged
     */
    @Override
    @Transactional
    public boolean finishDelivery(Long id) {
        log.info("Request DAO to finish Delivery id={}", id);
        return deliveryDAO.finishDelivery(id);
    }

    @Override
    public Delivery convertToEntity(DeliveryDTO dto) {
        return modelMapper.map(dto, Delivery.class);
    }

    /**
     * Transforms the list of separate cargo routes
     *
     * e.g. cargo A : 1-2
     *      cargo B : 2-3
     *      cargo C : 1-4
     *      cargo D : 1-4
     *
     * into the list of operations at each point:
     *
     * 1 : load cargo   A C D
     * 2 : unload cargo A
     *     load cargo   B
     * 3 : unload cargo B
     * 4 : unload cargo C D
     *
     * It is convenient to have data in this format
     * at front-end for further rendering.
     *
     * Recommendation: rewrite it with the usage of underlying
     * array with {@link CargoDTO} objects and links on them
     * to reduce object duplication.
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
            String pointNorm = geoService.format(point);
            routeMap.putIfAbsent(pointNorm, new DeliveryDTO.CargoOperations());
        }

        List<Cargo> cargoes = entity.getCargo();
        cargoes.forEach(cargo -> {
            GHGeocodingEntry startEntry = cargo.getStartDepot().getLocation();
            String startPoint = geoService.pointStringFromEntry(startEntry);
            String startPointNorm = geoService.format(startPoint);

            GHGeocodingEntry endEntry = cargo.getEndDepot().getLocation();
            String endPoint = geoService.pointStringFromEntry(endEntry);
            String endPointNorm = geoService.format(endPoint);

            CargoDTO cargoDTO = modelMapper.map(cargo, CargoDTO.class);

            routeMap.get(startPointNorm).addLoadOps(cargoDTO);
            routeMap.get(endPointNorm).addUnloadOps(cargoDTO);
        });

        DeliveryDTO deliveryDTO = modelMapper.map(entity, DeliveryDTO.class);
        deliveryDTO.setRouteWithCargoOperations(routeMap);

        return deliveryDTO;
    }

}

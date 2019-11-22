package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.DeliveryDAO;
import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.TaskDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.soap.Delivery;
import com.tsystems.transportinfo.soap.DeliveryList;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.TrucksStat;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TruckDAO truckDAO;

    @Autowired
    private DeliveryDAO deliveryDAO;

    @Autowired
    private DepotService depotService;

    @Override
    @Transactional
    @Synchronized
    public DriversStat getDriversStat() {
        int availableDrivers = driverDAO.calculateAvailableDrivers();
        int drivingDrivers = taskDAO.calculateDrivingDrivers();
        int numberOfDrivers = driverDAO.calculateDrivers();
        int otherDrivers = numberOfDrivers - availableDrivers - drivingDrivers;

        return new DriversStat(availableDrivers, drivingDrivers, otherDrivers, numberOfDrivers);
    }

    @Override
    @Transactional
    @Synchronized
    public TrucksStat getTrucksStat() {
        int defectiveTrucks = truckDAO.calculateDefectiveTrucks();
        int usedTrucks = truckDAO.calculateUsedTrucks();
        int numberOfTrucks = truckDAO.calculateTrucks();
        int availableTrucks = numberOfTrucks - usedTrucks - defectiveTrucks;

        return new TrucksStat(availableTrucks, defectiveTrucks, usedTrucks, numberOfTrucks);
    }

    @Override
    @Transactional
    @Synchronized
    public DeliveryList getDeliveryList(int limit) {
        List<com.tsystems.transportinfo.data.entity.Delivery> deliveryEntities = deliveryDAO.getLastDeliveries(limit);
        log.info("Received {} deliveries", deliveryEntities.size());

        List<Delivery> soapDeliveryList =
                        deliveryEntities
                        .stream()
                        .map(delivery -> {
                            long id = delivery.getId();
                            String truck = delivery.getTruck().getPlate();

                            List<String> cargoes = delivery.getCargo()
                                    .stream()
                                    .map(cargo ->
                                            String.format("#%d %s", cargo.getId(),
                                                                    cargo.getDescription()))
                                    .collect(Collectors.toList());

                            List<String> drivers = delivery.getDrivers()
                                    .stream()
                                    .map(driver ->
                                            String.format("#%d %s %s", driver.getId(),
                                                                       driver.getFirstName(),
                                                                       driver.getLastName()))
                                    .collect(Collectors.toList());

                            List<String> routePointsList = new ArrayList<>();

                            String routeJson = delivery.getRoute();
                            JSONObject jsonObject = new JSONObject(routeJson);
                            Iterator<String> iterator = jsonObject.keys();
                            while (iterator.hasNext()) {
                                routePointsList.add((String) jsonObject.get(iterator.next()));
                            }

                            String routeString = routePointsList
                                                    .stream()
                                                    .map(coords -> depotService.getDepotByCoords(coords)
                                                        .getLocation()
                                                        .getCity())
                                                    .collect(Collectors.joining(" &#8594; "));

                            return new Delivery(cargoes, drivers, id, routeString, truck);
                        })
                        .collect(Collectors.toList());

        return new DeliveryList(soapDeliveryList);
    }

}

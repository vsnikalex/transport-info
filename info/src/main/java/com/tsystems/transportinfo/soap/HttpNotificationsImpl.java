package com.tsystems.transportinfo.soap;

import com.tsystems.transportinfo.model.DeliveryList;
import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jws.WebService;

@Slf4j
@WebService(
        portName = "HttpNotificationsImplPort",
        serviceName = "NotificationsServiceLocal",
        endpointInterface = "com.tsystems.transportinfo.soap.Notifications",
        targetNamespace = "http://soap.transportinfo.tsystems.com/"
)
public class HttpNotificationsImpl implements Notifications {

    @Inject
    private Event<DriversStat> driversStatEvent;

    @Inject
    private Event<TrucksStat> trucksStatEvent;

    @Inject
    private Event<DeliveryList> deliveryListEvent;

    @Override
    public void updateDriversStat(DriversStat driversStat) {
        try {
            log.info("HTTP drivers stat, total: {}", driversStat.getTotal());
            driversStatEvent.fire(driversStat);
        } catch (NullPointerException e) {
            log.info("DriversStat is empty");
        }
    }

    @Override
    public void updateTrucksStat(TrucksStat trucksStat) {
        try {
            log.info("HTTP trucks stat, total: {}", trucksStat.getTotal());
            trucksStatEvent.fire(trucksStat);
        } catch (NullPointerException e) {
            log.info("TrucksStat is empty");
        }
    }

    @Override
    public void updateDeliveryList(DeliveryList deliveryList) {
        try {
            log.info("HTTP deliveries, total: {}", deliveryList.getDeliveries().size());
            deliveryListEvent.fire(deliveryList);
        } catch (NullPointerException e) {
            log.info("DeliveryList is empty");
        }
    }

}

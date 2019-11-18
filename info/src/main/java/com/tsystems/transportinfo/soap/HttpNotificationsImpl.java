package com.tsystems.transportinfo.soap;

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
        wsdlLocation = "META-INF/wsdl/NotificationsService.wsdl",
        endpointInterface = "com.tsystems.transportinfo.soap.Notifications",
        targetNamespace = "http://soap.transportinfo.tsystems.com/"
)
public class HttpNotificationsImpl implements Notifications {

    @Inject
    private Event<DriversStat> driversStatEvent;

    @Inject
    private Event<TrucksStat> trucksStatEvent;

    @Override
    public void updateDriversStat(DriversStat driversStat) {
        log.info("HTTP drivers stat, total: {}", driversStat.getTotal());
        driversStatEvent.fire(driversStat);
    }

    @Override
    public void updateTrucksStat(TrucksStat trucksStat) {
        log.info("HTTP trucks stat, total: {}", trucksStat.getTotal());
        trucksStatEvent.fire(trucksStat);
    }

}

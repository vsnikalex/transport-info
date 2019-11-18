package com.tsystems.transportinfo.soap;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@WebService(
        portName = "HttpNotificationsImplPort",
        serviceName = "NotificationsServiceLocal",
        wsdlLocation = "META-INF/wsdl/NotificationsService.wsdl",
        endpointInterface = "com.tsystems.transportinfo.soap.Notifications",
        targetNamespace = "http://soap.transportinfo.tsystems.com/"
)
public class HttpNotificationsImpl implements Notifications {

    @Override
    public void updateDriversStat(DriversStat driversStat) {
        log.info("HTTP drivers stat, total: {}", driversStat.getTotal());
    }

    @Override
    public void updateTrucksStat(TrucksStat trucksStat) {
        log.info("HTTP trucks stat, total: {}", trucksStat.getTotal());
    }

}

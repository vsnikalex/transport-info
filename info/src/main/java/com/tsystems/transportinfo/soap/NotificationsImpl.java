package com.tsystems.transportinfo.soap;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(
        portName = "NotificationsImplPort",
        serviceName = "NotificationsService",
        wsdlLocation = "META-INF/wsdl/NotificationsService.wsdl",
        endpointInterface = "com.tsystems.transportinfo.soap.Notifications",
        targetNamespace = "http://soap.transportinfo.tsystems.com/"
)
public class NotificationsImpl implements Notifications {

    @Inject
    Logger LOG;

    @Override
    public void updateDriversStat(DriversStat driversStat) {
        System.out.println("receive drivers stat, total: " + driversStat.getTotal());
    }

    @Override
    public void updateTrucksStat(TrucksStat trucksStat) {
        System.out.println("receive trucks stat, total: " + trucksStat.getTotal());
    }

}

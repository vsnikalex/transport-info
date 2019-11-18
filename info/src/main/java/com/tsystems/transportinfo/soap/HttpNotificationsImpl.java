package com.tsystems.transportinfo.soap;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(
        portName = "HttpNotificationsImplPort",
        serviceName = "NotificationsServiceLocal",
        wsdlLocation = "META-INF/wsdl/NotificationsService.wsdl",
        endpointInterface = "com.tsystems.transportinfo.soap.Notifications",
        targetNamespace = "http://soap.transportinfo.tsystems.com/"
)
public class HttpNotificationsImpl implements Notifications {

    @Inject
    Logger LOG;

    @Override
    public void updateDriversStat(DriversStat driversStat) {
        LOG.log(Level.INFO, "receive drivers stat, total: {0}", driversStat.getTotal());
    }

    @Override
    public void updateTrucksStat(TrucksStat trucksStat) {
        LOG.log(Level.INFO, "receive trucks stat, total: {0}", trucksStat.getTotal());
    }

}

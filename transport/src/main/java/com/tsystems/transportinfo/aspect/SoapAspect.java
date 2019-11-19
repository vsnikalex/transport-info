package com.tsystems.transportinfo.aspect;

import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.Notifications;
import com.tsystems.transportinfo.soap.NotificationsServiceLocal;
import com.tsystems.transportinfo.soap.TrucksStat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SoapAspect {

    @After("@annotation(DriverEvent)")
    public void sendDriversStat() {
        DriversStat driversStat = new DriversStat(
                (int) (Math.random() * 100),
                (int) (Math.random() * 100),
                (int) (Math.random() * 100),
                (int) (Math.random() * 100)
        );

        NotificationsServiceLocal notificationsService = new NotificationsServiceLocal();
        Notifications notifications =  notificationsService.getHttpNotificationsImplPort();
        notifications.updateDriversStat(driversStat);

        log.info("Driver transaction is successful");
    }

    @After("@annotation(TruckEvent)")
    public void sendTrucksStat() {
        TrucksStat trucksStat = new TrucksStat(
                (int) (Math.random() * 100),
                (int) (Math.random() * 100),
                (int) (Math.random() * 100),
                (int) (Math.random() * 100)
        );

        NotificationsServiceLocal notificationsService = new NotificationsServiceLocal();
        Notifications notifications =  notificationsService.getHttpNotificationsImplPort();
        notifications.updateTrucksStat(trucksStat);

        log.info("Truck transaction is successful");
    }

}

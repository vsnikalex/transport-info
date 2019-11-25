package com.tsystems.transportinfo.aspect;

import com.tsystems.transportinfo.service.StatService;
import com.tsystems.transportinfo.soap.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SoapAspect {

    @Autowired
    private StatService statService;

    @After("@annotation(DriverEvent)")
    public void sendDriversStat() {
        Notifications notifications = getNotificationService();

        if (null != notifications) {
            DriversStat driversStat = statService.getDriversStat();
            notifications.updateDriversStat(driversStat);
            log.info("DriversStat sent");
        }
    }

    @After("@annotation(TruckEvent)")
    public void sendTrucksStat() {
        Notifications notifications = getNotificationService();

        if (null != notifications) {
            TrucksStat trucksStat = statService.getTrucksStat();
            notifications.updateTrucksStat(trucksStat);
            log.info("TrucksStat sent");
        }
    }

    @After("@annotation(DeliveryEvent)")
    public void sendDeliveryList() {
        Notifications notifications = getNotificationService();

        if (null != notifications) {
            DeliveryList deliveryList = statService.getDeliveryList(10);
            notifications.updateDeliveryList(deliveryList);
            log.info("DeliveryList sent");
        }
    }

    private Notifications getNotificationService() {
        try {
            NotificationsServiceLocal notificationsService = new NotificationsServiceLocal();
            return notificationsService.getHttpNotificationsImplPort();
        } catch (Exception e) {
            log.info("SOAP service is not available");
            return null;
        }
    }

}

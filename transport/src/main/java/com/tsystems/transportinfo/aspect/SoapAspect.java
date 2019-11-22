package com.tsystems.transportinfo.aspect;

import com.tsystems.transportinfo.service.StatService;
import com.tsystems.transportinfo.soap.DeliveryList;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.Notifications;
import com.tsystems.transportinfo.soap.TrucksStat;
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

    @Autowired
    private Notifications notifications;

    @After("@annotation(DriverEvent)")
    public void sendDriversStat() {
        DriversStat driversStat = statService.getDriversStat();
       notifications.updateDriversStat(driversStat);
        log.info("Driver transaction is successful");
    }

    @After("@annotation(TruckEvent)")
    public void sendTrucksStat() {
        TrucksStat trucksStat = statService.getTrucksStat();
        notifications.updateTrucksStat(trucksStat);
        log.info("Truck transaction is successful");
    }

    @After("@annotation(DeliveryEvent)")
    public void sendDeliveryList() {
        DeliveryList deliveryList = statService.getDeliveryList(10);
        notifications.updateDeliveryList(deliveryList);
        log.info("Delivery transaction is successful");
    }

}

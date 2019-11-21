package com.tsystems.transportinfo.aspect;

import com.tsystems.transportinfo.service.StatService;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.Notifications;
import com.tsystems.transportinfo.soap.NotificationsServiceLocal;
import com.tsystems.transportinfo.soap.TrucksStat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SoapAspect {

    @Autowired
    private StatService statService;

    @Bean
    public Notifications getHttpSoapService() {
        NotificationsServiceLocal notificationsService = new NotificationsServiceLocal();
        return notificationsService.getHttpNotificationsImplPort();
    }

    @AfterReturning("@annotation(DriverEvent)")
    public void sendDriversStat() {
        DriversStat driversStat = statService.getDriversStat();
        getHttpSoapService().updateDriversStat(driversStat);
        log.info("Driver transaction is successful");
    }

    @AfterReturning("@annotation(TruckEvent)")
    public void sendTrucksStat() {
        TrucksStat trucksStat = statService.getTrucksStat();
        getHttpSoapService().updateTrucksStat(trucksStat);
        log.info("Truck transaction is successful");
    }

    @AfterReturning("@annotation(DeliveryEvent)")
    public void sendDeliveryList() {
//        TrucksStat trucksStat = statService.getTrucksStat();
//        getHttpSoapService().updateTrucksStat(trucksStat);
        log.info("Delivery transaction is successful");
    }

}

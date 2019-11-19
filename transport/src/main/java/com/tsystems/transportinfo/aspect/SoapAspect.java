package com.tsystems.transportinfo.aspect;

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
        log.info("After: Driver transaction is successful");
    }

    @After("@annotation(TruckEvent)")
    public void annotationSendTrucksStat() {
        log.info("After: Truck transaction is successful");
    }

}

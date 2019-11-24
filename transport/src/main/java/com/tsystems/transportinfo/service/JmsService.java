package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.aspect.SoapAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JmsService {

    @Autowired
    private SoapAspect soapAspect;

    @JmsListener(destination = "ModuleInteractionQueue")
    public void readInfoMessage(String message) {
       log.info("Received message from ModuleInteractionQueue: {}", message);
       if ("READY".equals(message)) {
           log.info("Send updates to Info");
           soapAspect.sendDeliveryList();
           soapAspect.sendDriversStat();
           soapAspect.sendTrucksStat();
       }
    }

}

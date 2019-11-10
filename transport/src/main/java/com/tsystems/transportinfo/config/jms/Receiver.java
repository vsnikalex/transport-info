package com.tsystems.transportinfo.config.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "truck-queue")
    public void receiveMessage(String order){
        System.out.println("Truck message = " + order);
    }

}

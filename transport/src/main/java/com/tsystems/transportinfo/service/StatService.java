package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.soap.DeliveryList;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.TrucksStat;

public interface StatService {

    DriversStat getDriversStat();

    TrucksStat getTrucksStat();

    DeliveryList getDeliveryList();

}

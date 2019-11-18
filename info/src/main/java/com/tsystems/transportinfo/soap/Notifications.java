package com.tsystems.transportinfo.soap;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Notifications {

    @WebMethod
    void updateDriversStat(DriversStat driversStat);

    @WebMethod
    void updateTrucksStat(TrucksStat trucksStat);

}

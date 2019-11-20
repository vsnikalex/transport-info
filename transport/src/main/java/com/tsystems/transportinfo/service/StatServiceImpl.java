package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.TaskDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.TrucksStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TruckDAO truckDAO;

    @Override
    public DriversStat getDriversStat() {
        int availableDrivers = driverDAO.calculateAvailableDrivers();
        int drivingDrivers = taskDAO.calculateDrivingDrivers();
        int numberOfDrivers = driverDAO.calculateDrivers();
        int otherDrivers = numberOfDrivers - availableDrivers - drivingDrivers;

        return new DriversStat(availableDrivers, drivingDrivers, otherDrivers, numberOfDrivers);
    }

    @Override
    public TrucksStat getTrucksStat() {
        return null;
    }

}

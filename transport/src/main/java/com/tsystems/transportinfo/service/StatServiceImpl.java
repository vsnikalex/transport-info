package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.dao.TaskDAO;
import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.soap.DeliveryList;
import com.tsystems.transportinfo.soap.DriversStat;
import com.tsystems.transportinfo.soap.TrucksStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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
        int defectiveTrucks = truckDAO.calculateDefectiveTrucks();
        int usedTrucks = truckDAO.calculateUsedTrucks();
        int numberOfTrucks = truckDAO.calculateTrucks();
        int availableTrucks = numberOfTrucks - usedTrucks - defectiveTrucks;

        return new TrucksStat(availableTrucks, defectiveTrucks, usedTrucks, numberOfTrucks);
    }

    @Override
    public DeliveryList getDeliveryList() {
        return null;
    }

}

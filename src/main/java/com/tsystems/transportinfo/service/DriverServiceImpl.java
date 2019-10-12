package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.DriverDAO;
import com.tsystems.transportinfo.data.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverDAO driverDAO;

    @Override
    public List<Driver> getAllDrivers() {
        return driverDAO.findAllDrivers();
    }

    @Override
    public void saveDriver(Driver driver) {
        driverDAO.saveDriver(driver);
    }

    @Override
    public Driver getDriver(Long id) {
        return driverDAO.findDriver(id);
    }

    @Override
    public void deleteDriver(Long id) {
        driverDAO.deleteDriver(id);
    }

}

package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverService {

    public List<Driver> getAllDrivers();

    public void saveDriver(Driver driver);

    public void updateDriver(Driver driver);

    public Driver getDriver(Long id);

    public void deleteDriver(Long id);

}

package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Driver;

import java.util.List;

public interface DriverDAO {

    public List<Driver> findAllDrivers();

    public void saveDriver(Driver driver);

    public Driver findDriver(Long id);

    public void deleteDriver(Long id);

}

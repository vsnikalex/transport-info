package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private GenericDAO<Driver> dao;

    @Autowired
    public void setDao(GenericDAO<Driver> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Driver.class);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return dao.findAll();
    }

    @Override
    public void saveDriver(Driver driver) {
        dao.create(driver);
    }

    @Override
    public Driver getDriver(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void deleteDriver(Long id) {
        dao.deleteById(id);
    }

}

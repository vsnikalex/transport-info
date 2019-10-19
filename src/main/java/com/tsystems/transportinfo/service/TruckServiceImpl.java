package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    private GenericDAO<Truck> dao;

    @Autowired
    public void setDao(GenericDAO<Truck> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Truck.class);
    }

    @Override
    public List<Truck> getAllTrucks() {
        return dao.findAll();
    }

    @Override
    public void saveTruck(Truck truck) {
        dao.create(truck);
    }

    @Override
    public void updateTruck(Truck truck) {
        dao.update(truck);
    }

    @Override
    public Truck getTruck(String plate) {
        return dao.findOneByStringId(plate);
    }

    @Override
    public void deleteTruck(String plate) {
        dao.deleteByStringId(plate);
    }

}

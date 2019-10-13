package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.TruckDAO;
import com.tsystems.transportinfo.data.entity.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    @Autowired
    TruckDAO truckDAO;

    @Override
    public List<Truck> getAllTrucks() {
        return truckDAO.findAllTrucks();
    }

    @Override
    public void saveTruck(Truck truck) {
        truckDAO.saveTruck(truck);
    }

    @Override
    public Truck getTruck(String plate) {
        return truckDAO.findTruck(plate);
    }

    @Override
    public void deleteTruck(String plate) {
        truckDAO.deleteTruck(plate);
    }

}

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
    public Truck getTruck(Long id) {
        return truckDAO.findTruck(id);
    }

    @Override
    public void deleteTruck(Long id) {
        truckDAO.deleteTruck(id);
    }

}

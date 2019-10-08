package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoDAO cargoDAO;

    @Override
    public List<Cargo> getCargoes() {
        return cargoDAO.getAllCargoes();
    }

    @Override
    public void saveCargo(Cargo cargo) {
        cargoDAO.saveCargo(cargo);
    }

    @Override
    public Cargo getCargo(Long id) {
        return cargoDAO.getCargo(id);
    }

    @Override
    public void deleteCargo(Long id) {
        cargoDAO.deleteCargo(id);
    }

}

package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.CargoDAO;
import com.tsystems.transportinfo.data.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    @Autowired
    CargoDAO cargoDAO;

    @Override
    public List<Cargo> getAllCargoes() {
        return cargoDAO.findAllCargoes();
    }

    @Override
    public void saveCargo(Cargo cargo) {
        cargoDAO.saveCargo(cargo);
    }

    @Override
    public Cargo getCargo(Long id) {
        return cargoDAO.findCargo(id);
    }

    @Override
    public void deleteCargo(Long id) {
        cargoDAO.deleteCargo(id);
    }

}

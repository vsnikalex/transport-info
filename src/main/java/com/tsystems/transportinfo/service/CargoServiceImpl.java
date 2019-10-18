package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private GenericDAO<Cargo> dao;

    @Autowired
    public void setDao(GenericDAO<Cargo> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Cargo.class);
    }

    @Override
    public List<Cargo> getAllCargoes() {
        return dao.findAll();
    }

    @Override
    public void saveCargo(Cargo cargo) {
        dao.create(cargo);
    }

    @Override
    public void updateCargo(Cargo cargo) {
        dao.update(cargo);
    }

    @Override
    public Cargo getCargo(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void deleteCargo(Long id) {
        dao.deleteById(id);
    }

}

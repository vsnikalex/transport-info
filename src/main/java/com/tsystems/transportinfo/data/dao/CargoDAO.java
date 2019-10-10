package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoDAO {

    public List<Cargo> findAllCargoes();

    public void saveCargo(Cargo cargo);

    public Cargo findCargo(Long id);

    public void deleteCargo(Long id);

}

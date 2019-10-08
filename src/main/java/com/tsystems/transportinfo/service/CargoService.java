package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoService {

    public List<Cargo> getCargoes();

    public void saveCargo(Cargo cargo);

    public Cargo getCargo(Long id);

    public void deleteCargo(Long id);

}

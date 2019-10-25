package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoDAO {
    List<Cargo> findByDepotId(Long id);
}

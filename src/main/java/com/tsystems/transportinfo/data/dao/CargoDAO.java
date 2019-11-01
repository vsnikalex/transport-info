package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;

import java.util.List;

public interface CargoDAO {

    List<Cargo> findByDepotId(Long id);

    Cargo findCargo(long id);

    void updateCargo(Cargo cargo);

    void assignToDelivery(long cargoID, long deliveryID);

}

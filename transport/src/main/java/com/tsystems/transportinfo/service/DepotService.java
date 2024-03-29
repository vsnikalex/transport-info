package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Depot;

import java.util.List;

public interface DepotService {

    List<Depot> getAllDepots();

    Depot getDepotById(Long id);

    Depot getDepotByCoords(String coords);

}

package com.tsystems.transportinfo.service;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.dao.DepotDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Depot;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DepotServiceImpl implements DepotService {

    @Autowired
    private DepotDAO depotDAO;

    private GenericDAO<Depot> dao;

    @Autowired
    public void setDao(GenericDAO<Depot> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Depot.class);
    }

    @Override
    @Transactional
    @Synchronized
    public List<Depot> getAllDepots() {
        log.info("Request DepotDAO to find all Depots");
        return dao.findAll();
    }

    @Override
    @Transactional
    public Depot getDepotById(Long id) {
        log.info("Request DepotDAO to find Depot id={}", id);
        return dao.findOne(id);
    }

    @Override
    @Transactional
    public Depot getDepotByCoords(String coords) {
        log.info("Request DepotDAO to find Depot with coordinates = {}", coords);
        return depotDAO.findDepotByCoords(GHPoint.fromString(coords));
    }

}

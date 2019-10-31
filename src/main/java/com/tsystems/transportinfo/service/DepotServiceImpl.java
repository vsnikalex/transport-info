package com.tsystems.transportinfo.service;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.dao.DepotDAO;
import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Depot;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepotServiceImpl implements DepotService {

    private GenericDAO<Depot> dao;

    @Autowired
    public void setDao(GenericDAO<Depot> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Depot.class);
    }

    @Autowired
    private DepotDAO depotDAO;

    @Override
    @Synchronized
    public List<Depot> getAllDepots() {
        return dao.findAll();
    }

    @Override
    public Depot getDepotById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Depot getDepotByCoords(String coords) {
        return depotDAO.findDepotByCoords(GHPoint.fromString(coords));
    }

}

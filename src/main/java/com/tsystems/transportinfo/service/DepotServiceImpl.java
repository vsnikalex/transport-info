package com.tsystems.transportinfo.service;

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

    @Override
    @Synchronized
    public List<Depot> getAllDepots() {
        return dao.findAll();
    }

    @Override
    public Depot getDepot(Long id) {
        return dao.findOne(id);
    }

}

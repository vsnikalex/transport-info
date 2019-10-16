package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.GenericDAO;
import com.tsystems.transportinfo.data.entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private GenericDAO<Delivery> dao;

    @Autowired
    public void setDao(GenericDAO<Delivery> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Delivery.class);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return dao.findAll();
    }

    @Override
    public void saveDelivery(Delivery delivery) {
        dao.create(delivery);
    }

    @Override
    public Delivery getDelivery(Long id) {
        return dao.findOne(id);
    }

    @Override
    public void deleteDelivery(Long id) {
        dao.deleteById(id);
    }

}

package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.dao.DeliveryDAO;
import com.tsystems.transportinfo.data.entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDAO deliveryDAO;

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryDAO.findAllDeliveries();
    }

    @Override
    public void saveDelivery(Delivery delivery) {
        deliveryDAO.saveDelivery(delivery);
    }

    @Override
    public Delivery getDelivery(Long id) {
        return deliveryDAO.findDelivery(id);
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryDAO.deleteDelivery(id);
    }

}

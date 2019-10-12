package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.Delivery;

import java.util.List;

public interface DeliveryService {

    public List<Delivery> getAllDeliveries();

    public void saveDelivery(Delivery delivery);

    public Delivery getDelivery(Long id);

    public void deleteDelivery(Long id);

}

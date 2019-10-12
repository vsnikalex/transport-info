package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Delivery;

import java.util.List;

public interface DeliveryDAO {

    public List<Delivery> findAllDeliveries();

    public void saveDelivery(Delivery delivery);

    public Delivery findDelivery(Long id);

    public void deleteDelivery(Long id);

}

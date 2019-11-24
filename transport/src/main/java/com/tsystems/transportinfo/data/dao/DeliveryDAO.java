package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Delivery;

import java.util.List;

public interface DeliveryDAO {

    List<Delivery> getLastDeliveries(int limit);

    boolean finishDelivery(long id);

    Delivery findDelivery(long id);

    void updateDelivery(Delivery delivery);

}

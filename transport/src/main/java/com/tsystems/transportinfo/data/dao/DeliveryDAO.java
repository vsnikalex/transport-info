package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Delivery;

import java.util.List;

public interface DeliveryDAO {

    List<Delivery> getLastDeliveries(int limit);

    void finishDelivery(long id);

}

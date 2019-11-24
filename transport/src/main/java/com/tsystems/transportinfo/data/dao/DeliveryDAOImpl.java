package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Delivery_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Delivery> getLastDeliveries(int limit) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT d FROM Delivery d ORDER BY " + Delivery_.CREATED + " DESC", Delivery.class)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public void finishDelivery(long id) {
        Session session = sessionFactory.getCurrentSession();

    }

}

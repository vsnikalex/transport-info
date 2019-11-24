package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Delivery_;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
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
    public Delivery findDelivery(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Delivery.class, id);
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(delivery);
    }

    @Override
    public void finishDelivery(long id) {
        Delivery delivery = findDelivery(id);

        Cargo undeliveredCargo =
                delivery.getCargo()
                .stream()
                .filter(cargo -> cargo.getStatus() != CargoStatus.DELIVERED)
                .findFirst().orElse(null);

        if (undeliveredCargo == null) {
            delivery.setDone(true);
            updateDelivery(delivery);
        }
    }

}

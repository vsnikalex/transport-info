package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Delivery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Delivery> findAllDeliveries() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Delivery> cq = cb.createQuery(Delivery.class);
        Root<Delivery> root = cq.from(Delivery.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveDelivery(Delivery delivery) {

    }

    @Override
    public Delivery findDelivery(Long id) {
        return null;
    }

    @Override
    public void deleteDelivery(Long id) {

    }
}

package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Driver;
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
public class DriverDAOImpl implements DriverDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Driver> findAllDrivers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);
        Root<Driver> root = cq.from(Driver.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveDriver(Driver driver) {

    }

    @Override
    public Driver findDriver(Long id) {
        return null;
    }

    @Override
    public void deleteDriver(Long id) {

    }
}

package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.data.entity.Driver_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(driver);
    }

    @Override
    public Driver findDriver(Long id) {
        return null;
    }

    @Override
    public void deleteDriver(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<Driver> criteriaDelete = cb.createCriteriaDelete(Driver.class);
        Root<Driver> root = criteriaDelete.from(Driver.class);

        criteriaDelete.where(cb.equal(root.get(Driver_.id), id));

        session.createQuery(criteriaDelete).executeUpdate();
    }

}

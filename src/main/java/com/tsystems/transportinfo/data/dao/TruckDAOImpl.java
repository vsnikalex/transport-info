package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.data.entity.Truck_;
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
public class TruckDAOImpl implements TruckDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Truck> findAllTrucks() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Truck> cq = cb.createQuery(Truck.class);
        Root<Truck> root = cq.from(Truck.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveTruck(Truck truck) {

    }

    @Override
    public Truck findTruck(String plate) {
        return null;
    }

    @Override
    public void deleteTruck(String plate) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<Truck> criteriaDelete = cb.createCriteriaDelete(Truck.class);
        Root<Truck> root = criteriaDelete.from(Truck.class);

        criteriaDelete.where(cb.equal(root.get(Truck_.plate), plate));

        session.createQuery(criteriaDelete).executeUpdate();
    }

}

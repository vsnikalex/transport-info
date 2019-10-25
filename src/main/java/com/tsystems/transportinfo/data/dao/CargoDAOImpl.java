package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.Cargo_;
import com.tsystems.transportinfo.data.entity.Depot;
import com.tsystems.transportinfo.data.entity.Depot_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CargoDAOImpl implements CargoDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Cargo> findByDepotId(Long id) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cargo> cq = cb.createQuery(Cargo.class);

        Root<Cargo> cargo = cq.from(Cargo.class);
        Join<Cargo, Depot> depot = cargo.join(Cargo_.location);

        cq.select(cargo).where(cb.equal(depot.get(Depot_.id), id));

        Query<Cargo> query = session.createQuery(cq);
        return query.getResultList();
    }

}

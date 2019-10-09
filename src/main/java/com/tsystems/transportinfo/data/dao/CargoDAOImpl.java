package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Cargo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CargoDAOImpl implements CargoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Cargo> getAllCargoes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cargo> cq = cb.createQuery(Cargo.class);
        Root<Cargo> root = cq.from(Cargo.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveCargo(Cargo cargo) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(cargo);
    }

    @Override
    public Cargo getCargo(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Cargo.class, id);
    }

    @Override
    public void deleteCargo(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Cargo cargo = session.byId(Cargo.class).load(id);
        session.delete(cargo);
    }

}
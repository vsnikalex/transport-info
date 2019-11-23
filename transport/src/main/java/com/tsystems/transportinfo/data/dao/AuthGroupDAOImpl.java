package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.AuthGroup;
import com.tsystems.transportinfo.data.entity.AuthGroup_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AuthGroupDAOImpl implements AuthGroupDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<AuthGroup> findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AuthGroup> cq = cb.createQuery(AuthGroup.class);

        Root<AuthGroup> authGroup = cq.from(AuthGroup.class);
        cq.where(cb.equal(authGroup.get(AuthGroup_.username), username));

        Query<AuthGroup> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void grantAuthority(String username, String role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(new AuthGroup(username, role));
    }

}

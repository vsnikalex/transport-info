package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.User;
import com.tsystems.transportinfo.data.entity.User_;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Slf4j
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> user = cq.from(User.class);
        cq.where(cb.equal(user.get(User_.username), username));

        Query<User> query = session.createQuery(cq);

        return query.getSingleResult();
    }

}

package com.tsystems.transportinfo.data.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HibernateDAO<T> implements GenericDAO<T> {
    private Class<T> clazz;

    @Autowired
    SessionFactory sessionFactory;

    public void setClazz(Class< T > clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(Long id){
        return getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(T entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(Long id) {
        T entity = findOne(id);
        delete(entity);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}

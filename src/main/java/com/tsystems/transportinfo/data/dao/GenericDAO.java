package com.tsystems.transportinfo.data.dao;

import java.util.List;

public interface GenericDAO<T> {

    void setClazz(Class< T > clazzToSet);

    T findOne(Long id);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(Long id);

}

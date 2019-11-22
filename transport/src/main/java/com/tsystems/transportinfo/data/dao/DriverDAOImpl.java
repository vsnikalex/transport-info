package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.data.entity.Driver_;
import com.tsystems.transportinfo.service.GeoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
public class DriverDAOImpl implements DriverDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GeoService geoService;

    @Override
    public int calculateDrivers() {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder qb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Driver.class)));
        long numOfDrivers = session.createQuery(cq).getSingleResult();

        return (int) numOfDrivers;
    }

    @Override
    public List<Driver> findAvailableDrivers(GHGeocodingEntry city) {
        Session session = sessionFactory.getCurrentSession();
        Stream<Driver> drivers = session.createQuery("SELECT d FROM Driver d", Driver.class).stream();

        return drivers.filter(d -> {
            boolean driverInSameCity = geoService.inSameCity(d.getLocation(), city);
            boolean driverIsFree = (d.getDelivery() == null) || (d.getDelivery().isDone());

            return driverInSameCity && driverIsFree;
        }).collect(Collectors.toList());
    }

    @Override
    public int calculateAvailableDrivers() {
        Session session = sessionFactory.getCurrentSession();
        Stream<Driver> drivers = session.createQuery("SELECT d FROM Driver d", Driver.class).stream();

        return (int) drivers
                .filter(d -> (d.getDelivery() == null) || (d.getDelivery().isDone()))
                .count();
    }

    @Override
    public Driver findDriver(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Driver.class, id);
    }

    @Override
    public long getIdByUsername(String username) {
        log.info("Request Driver id with username {} from database", username);

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Driver> cq = cb.createQuery(Driver.class);

        Root<Driver> user = cq.from(Driver.class);
        cq.where(cb.equal(user.get(Driver_.username), username));

        Query<Driver> query = session.createQuery(cq);

        Long id = -1L;
        try {
            id = query.getSingleResult().getId();
        } catch (NoResultException | NullPointerException e) {
            log.error("No driver {} found", username);
        }

        log.info("Found driver id: {}", id);
        return id;
    }

    @Override
    public void updateDriver(Driver driver) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(driver);
    }

    @Override
    public void assignToDelivery(long driverId, long deliveryID) {
        Driver driver = findDriver(driverId);
        Delivery delivery = new Delivery(deliveryID);
        driver.setDelivery(delivery);
        updateDriver(driver);
    }

}

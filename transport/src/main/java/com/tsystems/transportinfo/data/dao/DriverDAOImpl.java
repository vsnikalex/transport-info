package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DriverDAOImpl implements DriverDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GraphHopperService graphHopperService;

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
            boolean driverInSameCity = graphHopperService.inSameCity(d.getLocation(), city);
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

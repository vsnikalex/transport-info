package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.data.entity.Driver;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Driver> findDriversByCity(GHGeocodingEntry city) {
        Session session = sessionFactory.getCurrentSession();

        Stream<Driver> drivers = session.createQuery("SELECT d FROM Driver d", Driver.class).stream();

        return drivers.filter(d -> graphHopperService.inSameCity(d.getLocation(), city))
                        .collect(Collectors.toList());
    }

}
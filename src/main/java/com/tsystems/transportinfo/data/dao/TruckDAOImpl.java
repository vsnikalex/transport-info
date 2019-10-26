package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.service.GraphHopperService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class TruckDAOImpl implements TruckDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GraphHopperService graphHopperService;

    @Override
    public List<Truck> findNearestTrucks(GHPoint destination, long maxTravelTime) {
        Session session = sessionFactory.getCurrentSession();

        Stream<Truck> trucks = session.createQuery("SELECT t FROM Truck t", Truck.class).stream();

        return trucks.filter(t -> {
            GHPoint truckPoint = graphHopperService.pointFromEntry(t.getLocation());
            long timeOfPath = graphHopperService.timeOfPath(truckPoint, destination);
            return timeOfPath <= maxTravelTime;
        }).collect(Collectors.toList());
    }

}

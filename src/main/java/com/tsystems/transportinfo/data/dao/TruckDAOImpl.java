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
        // Use rough estimate to calculate distance between trucks and destination
        long maxTravelHours = maxTravelTime / 1000 / 60 / 60;
        long maxTravelDistanceMeters = maxTravelHours * 60 * 1000;

        Session session = sessionFactory.getCurrentSession();

        Stream<Truck> trucks = session.createQuery("SELECT t FROM Truck t", Truck.class).stream();

        return trucks.filter(t -> {
            GHPoint truckPoint = graphHopperService.pointFromEntry(t.getLocation());
            double distanceMeters = graphHopperService.distance(
                                                    truckPoint.getLat(), destination.getLat(),
                                                    truckPoint.getLon(), destination.getLon(), 0, 0);
            return distanceMeters < maxTravelDistanceMeters;
        }).collect(Collectors.toList());
    }

}

package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Delivery;
import com.tsystems.transportinfo.data.entity.Truck;
import com.tsystems.transportinfo.data.entity.enums.TruckStatus;
import com.tsystems.transportinfo.service.GeoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
public class TruckDAOImpl implements TruckDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GeoService geoService;

    @Override
    public int calculateTrucks() {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder qb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Truck.class)));
        long numOfTrucks = session.createQuery(cq).getSingleResult();

        return (int) numOfTrucks;
    }

    @Override
    public int calculateUsedTrucks() {
        Session session = sessionFactory.getCurrentSession();
        Stream<Truck> trucks = session.createQuery("SELECT t FROM Truck t", Truck.class).stream();

        return (int) trucks.filter(truck ->
                (truck.getDelivery() != null)
                        && !(truck.getDelivery().isDone()))
                .count();
    }

    @Override
    public int calculateDefectiveTrucks() {
        Session session = sessionFactory.getCurrentSession();
        Stream<Truck> trucks = session.createQuery("SELECT t FROM Truck t", Truck.class).stream();

        return (int) trucks.filter(truck ->
                (truck.getStatus().equals(TruckStatus.DEFECTIVE)))
                .count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Truck> findAvailableTrucks(GHPoint destination, long maxTravelTime) {
        // Use rough estimate to calculate distance between trucks and destination point
        long maxTravelHours = maxTravelTime / 1000 / 60 / 60;
        long maxTravelDistanceMeters = maxTravelHours * 60 * 1000;

        Session session = sessionFactory.getCurrentSession();
        Stream<Truck> trucks = session.createQuery("SELECT t FROM Truck t", Truck.class).stream();

        return trucks.filter(t -> {
            GHPoint truckPoint = geoService.pointFromEntry(t.getLocation());
            double distanceMeters = geoService.distance(
                                                    truckPoint.getLat(), destination.getLat(),
                                                    truckPoint.getLon(), destination.getLon(), 0, 0);

            boolean distanceIsOk = distanceMeters < maxTravelDistanceMeters;
            boolean statusIsOk = t.getStatus() == TruckStatus.OK;
            boolean truckIsFree = (t.getDelivery() == null) || (t.getDelivery().isDone());

            return distanceIsOk && statusIsOk && truckIsFree;
        }).collect(Collectors.toList());
    }

    @Override
    public Truck findTruck(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Truck.class, id);
    }

    @Override
    public boolean deleteTruck(long id) {
        Truck truck = findTruck(id);
        if (truck == null) { return false; }

        Delivery trucksDelivery = truck.getDelivery();

        if (trucksDelivery == null) {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.delete(truck);
            return true;
        } else {
            log.info("Attempt to delete Truck id={} assigned to Delivery id={}",
                    truck.getId(), trucksDelivery.getId());
            return false;
        }
    }

}

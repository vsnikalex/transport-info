package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.data.entity.Depot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class DepotDAOImpl implements DepotDAO {

    private static final double GEO_ACCURACY = 0.00001;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Depot findDepotByCoords(GHPoint point) {
        Session session = sessionFactory.getCurrentSession();
        Stream<Depot> depots = session.createQuery("SELECT d FROM Depot d", Depot.class).stream();

        return depots.filter(d -> {
            GHGeocodingEntry.Point depotPoint = d.getLocation().getPoint();

            double pointLat = point.getLat();
            double depotLat = depotPoint.getLat();
            double latDif = pointLat - depotLat;
            boolean equalLats = Math.abs(latDif) <= GEO_ACCURACY;

            double pointLng = point.getLon();
            double depotLng = depotPoint.getLng();
            double lngDif = pointLng - depotLng;
            boolean equalLngs = Math.abs(lngDif) <= GEO_ACCURACY;

            return equalLats && equalLngs;
        }).findFirst().orElse(null);
    }

}

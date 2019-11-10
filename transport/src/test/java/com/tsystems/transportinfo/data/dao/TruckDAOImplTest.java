package com.tsystems.transportinfo.data.dao;

import com.graphhopper.util.shapes.GHPoint;
import com.tsystems.transportinfo.config.HibernateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class TruckDAOImplTest {

    @Autowired
    TruckDAO truckDAO;

    @Test
    @Transactional
    public void searchIsValid() {
        GHPoint point = new GHPoint(47.68664, 13.09849);
        truckDAO.findAvailableTrucks(point,2 * 60 * 60 * 1000L).stream()
                                        .map(t -> t.getLocation().getCountry() + " " + t.getPlate())
                                        .forEach(System.out::println);
    }

}


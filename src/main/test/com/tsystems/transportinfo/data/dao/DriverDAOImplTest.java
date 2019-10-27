package com.tsystems.transportinfo.data.dao;

import com.graphhopper.api.model.GHGeocodingEntry;
import com.tsystems.transportinfo.config.HibernateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class DriverDAOImplTest {

    @Autowired
    DriverDAO driverDAO;

    @Test
    @Transactional
    public void searchIsValid() {
        GHGeocodingEntry geocodingEntry = new GHGeocodingEntry();
        geocodingEntry.setCountry("Austria");
        geocodingEntry.setState("Salzburg");
        geocodingEntry.setCity("Hallein");

        driverDAO.findDriversByCity(geocodingEntry)
                                        .stream()
                                        .map(d -> d.getFirstName() + " " + d.getLastName())
                                        .forEach(System.out::println);
    }

}

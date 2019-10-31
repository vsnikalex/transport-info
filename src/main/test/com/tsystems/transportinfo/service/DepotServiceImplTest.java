package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.config.HibernateConfig;
import com.tsystems.transportinfo.data.entity.Depot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class DepotServiceImplTest {

    @Autowired
    private DepotService depotService;

    @Test
    @Transactional
    public void searchIsValid() {
        Depot depot = depotService.getDepotByCoords("46.094432,11.115757");

        assertNotNull(depot);

        System.out.println(depot.getLocation().getCity() + " " + depot.getType());
    }

}

package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.config.HibernateConfig;
import com.tsystems.transportinfo.data.entity.Cargo;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class })
public class CargoDAOTest {

    @Autowired
    CargoDAO cargoDAO;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback
    public void testFindById(){
        Session session = sessionFactory.getCurrentSession();

        Cargo toSend = new Cargo();
        toSend.setLocation("Moscow");
        toSend.setDescription("IKEA Sofa");
        toSend.setStatus(CargoStatus.SHIPPED);
        toSend.setWeight(100);

        session.save(toSend);
        session.flush();

        Cargo got = cargoDAO.findCargo(1L);
        Assert.assertNotNull(got);
        Assert.assertEquals("Moscow", got.getLocation());
        Assert.assertEquals("IKEA Sofa", got.getDescription());
        Assert.assertEquals(CargoStatus.SHIPPED, got.getStatus());
        Assert.assertEquals(100, got.getWeight());
    }

}

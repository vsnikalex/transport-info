package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.config.HibernateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    @Transactional
    public void calculationIsValid() {
        double hours = taskService
                        .getFutureWorkedHours(4L, 1574431200);

        System.out.println(hours);
    }

}

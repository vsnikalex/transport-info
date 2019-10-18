package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.config.HibernateConfig;
import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class CargoDTOTest {

    @Autowired
    private Validator validator;

    @Test
    public void cargoIsValid() {
        CargoDTO cargoDTO = new CargoDTO(1L, "IKEA Sofa", 100, CargoStatus.SHIPPED,
                                "48.7525249, 18.1450552", "48.7525249, 18.1450552");

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargoDTO);

        assertEquals(0, constraintViolations.size());
    }

}

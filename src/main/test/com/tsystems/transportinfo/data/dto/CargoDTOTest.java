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
    public void locationIsNull() {
        CargoDTO cargo = new CargoDTO(null, "Test", 100, CargoStatus.SHIPPED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Location must not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void descriptionIsNull() {
        CargoDTO cargo = new CargoDTO("TestBurg",null, 100, CargoStatus.SHIPPED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Description must not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void overweight() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 28_000, CargoStatus.PREPARED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Weight must be between 100 and 27000",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void statusIsNull() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 100, null);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals("Status must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void cargoIsValid() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 13_500, CargoStatus.DELIVERED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(0, constraintViolations.size());
    }

}

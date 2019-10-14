package com.tsystems.transportinfo.data.dto;

import com.tsystems.transportinfo.data.entity.enums.CargoStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CargoDTOTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void locationIsNull() {
        CargoDTO cargo = new CargoDTO(null, "Test", 100, CargoStatus.SHIPPED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(2, constraintViolations.size());

        int emptyParam = (int) constraintViolations.stream()
                                                    .filter(c -> c.getMessage().equals("must not be empty"))
                                                    .count();
        assertEquals(1, emptyParam);

        int nullParam = (int) constraintViolations.stream()
                                                    .filter(c -> c.getMessage().equals("must not be null"))
                                                    .count();
        assertEquals(1, nullParam);
    }

    @Test
    public void descriptionIsNull() {
        CargoDTO cargo = new CargoDTO("TestBurg",null, 100, CargoStatus.SHIPPED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(2, constraintViolations.size());

        int emptyParam = (int) constraintViolations.stream()
                                                    .filter(c -> c.getMessage().equals("must not be empty"))
                                                    .count();
        assertEquals(1, emptyParam);

        int nullParam = (int) constraintViolations.stream()
                                                    .filter(c -> c.getMessage().equals("must not be null"))
                                                    .count();
        assertEquals(1, nullParam);
    }

    @Test
    public void overweight() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 28_000, CargoStatus.PREPARED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must be between 100 and 27000",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void statusIsNull() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 100, null);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void cargoIsValid() {
        CargoDTO cargo = new CargoDTO("TestBurg","Test", 13_500, CargoStatus.DELIVERED);

        Set<ConstraintViolation<CargoDTO>> constraintViolations =
                validator.validate(cargo);

        assertEquals(0, constraintViolations.size());
    }

}

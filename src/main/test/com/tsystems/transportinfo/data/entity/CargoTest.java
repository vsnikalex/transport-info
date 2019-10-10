package com.tsystems.transportinfo.data.entity;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CargoTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void descriptionIsNull() {
        Cargo cargo = new Cargo(null, 100, CargoStatus.SHIPPED);

        Set<ConstraintViolation<Cargo>> constraintViolations =
                validator.validate(cargo);

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void overweight() {
        Cargo cargo = new Cargo("Test", 28_000, CargoStatus.PREPARED);

        Set<ConstraintViolation<Cargo>> constraintViolations =
                validator.validate(cargo);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be less than or equal to 27000",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void statusIsNull() {
        Cargo cargo = new Cargo("Test", 100, null);

        Set<ConstraintViolation<Cargo>> constraintViolations =
                validator.validate(cargo);

        assertEquals( 1, constraintViolations.size() );
        assertEquals( "may not be null", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void cargoIsValid() {
        Cargo cargo = new Cargo("Test", 13_500, CargoStatus.DELIVERED);

        Set<ConstraintViolation<Cargo>> constraintViolations =
                validator.validate(cargo);

        assertEquals( 0, constraintViolations.size() );
    }

}
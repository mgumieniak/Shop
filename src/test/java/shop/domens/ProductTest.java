package shop.domens;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    private static Validator validator;
    private Product product;

    @BeforeClass
    public static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void initialProduct(){
        product = new Product();
    }

    @Test
    public void toShortProductName(){

        product = new Product("", new BigDecimal(1000), "test", "test",
                "test", 10, 0, false, Product.Condition.NEW);

        System.out.print(product);
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Invalid name. The correct name contain 3-60 characters",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void toLongProductName(){

        product = new Product("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest",
                new BigDecimal(1000), "test", "test",
                "test", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Invalid name. The correct name contain 3-60 characters",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void validProduct(){

        product = new Product("tes",
                new BigDecimal(1000), "te", "te",
                "te", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(0, constraintViolations.size())
        );
    }

    @Test
    public void negativePrice() {
        product = new Product("test",
                new BigDecimal(-100), "test", "test",
                "test", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("The price can not be negative",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void descriptionToShort() {
        product = new Product("test",
                new BigDecimal(100), "t", "test",
                "test", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Description is required",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void manufacturerNameToShort() {
        product = new Product("test",
                new BigDecimal(100), "test", "t",
                "te", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Producer is required",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void categoryNameToShort() {
        product = new Product("test",
                new BigDecimal(100), "test", "test",
                "t", 10, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Category is required",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void invalidAmountProduct() {
        product = new Product("test",
                new BigDecimal(100), "test", "test",
                "test", 1000000000, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Invalid units in stock format",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void negativeAmountProduct() {
        product = new Product("test",
                new BigDecimal(100), "test", "test",
                "test", -5, 0, false, Product.Condition.NEW);

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()->assertEquals("Units in stock must be positive",
                        constraintViolations.iterator().next().getMessage())
        );
    }
}


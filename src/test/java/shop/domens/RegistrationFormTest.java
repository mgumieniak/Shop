package shop.domens;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationFormTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private RegistrationForm factoryRegistrationForm(String username, String password, String confirmPassword){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUsername(username);
        registrationForm.setPassword(password);
        registrationForm.setConfirmPassword(confirmPassword);
        return registrationForm;
    }

    @Test
    public void testEqualPasswords(){
        RegistrationForm registrationForm = factoryRegistrationForm("test","test","testNotEqual");

        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validate(registrationForm);

        assertAll(
                ()->assertEquals(1, constraintViolations.size()),
                ()-> assertEquals("Field Password and Repeat Password is not equal.",
                        constraintViolations.iterator().next().getMessage())
        );
    }

    @Test
    public void testPasswordMustContain2Elements(){
        RegistrationForm registrationForm = factoryRegistrationForm("test","t","t");

        Set<ConstraintViolation<RegistrationForm>> constraintViolations = validator.validate(registrationForm);

        assertEquals(1, constraintViolations.size());
        assertEquals("Password must contain minimum 2 elements",
                constraintViolations.iterator().next().getMessage());
    }


}


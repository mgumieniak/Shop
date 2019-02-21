package shop.validator;

import shop.domens.RegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, RegistrationForm> {

    public EqualPasswordsValidator() {
    }


    @Override
    public void initialize(EqualPasswords constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegistrationForm registrationForm, ConstraintValidatorContext context) {
        return registrationForm.getPassword().equals(registrationForm.getConfirmPassword());
    }
}

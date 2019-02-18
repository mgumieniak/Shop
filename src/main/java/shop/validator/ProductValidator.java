package shop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import shop.domens.Product;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;


public class ProductValidator implements Validator {

    @Autowired
    private javax.validation.Validator beanValidator;

    private Set<Validator> springValidators;

    public ProductValidator() {
        this.springValidators = new HashSet<>();
    }

    public ProductValidator(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Set<ConstraintViolation<Object>> constraintViolations =beanValidator.validate(object);

        for(ConstraintViolation<Object> constraintViolation: constraintViolations){
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
        for(Validator validator: springValidators) {
            validator.validate(object, errors);
        }
    }

    public Set<Validator> getSpringValidators() {
        return springValidators;
    }

    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
}

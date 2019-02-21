package shop.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PriceAndAmountOfProductValidator.class)
@Documented
public @interface PriceAndAmountOfProduct {
    String message() default "{com.shop.validator.PriceAndAmountOfProductValidator.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

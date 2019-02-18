package shop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import shop.domens.Product;

import java.math.BigDecimal;

@Component
public class UnitsInStockValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Product product = (Product) object;

        if(product.getUnitPrice() != null && new BigDecimal(100000)
                .compareTo(product.getUnitPrice())<=0 && product.getUnitsInStock()>99){
            errors.rejectValue("unitsInStock","com.shop.validator.UnitsInStockValidator.message");
        }
    }
}

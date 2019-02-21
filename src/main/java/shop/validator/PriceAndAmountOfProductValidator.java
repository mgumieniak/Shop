package shop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import shop.data.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceAndAmountOfProductValidator implements ConstraintValidator<PriceAndAmountOfProduct, Integer> {

   private UserRepository userRepository;

   public PriceAndAmountOfProductValidator(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public PriceAndAmountOfProductValidator() {
   }

   public void initialize(PriceAndAmountOfProduct constraint) {
   }

   public boolean isValid(Integer obj, ConstraintValidatorContext context) {
      if(obj>10){
         return true;
      }else
         return false;
   }
}

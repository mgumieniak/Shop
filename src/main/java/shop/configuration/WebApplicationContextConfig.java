package shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import shop.validator.ProductValidator;
import shop.validator.UnitsInStockValidator;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ProductValidator productValidator () {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UnitsInStockValidator());
        ProductValidator productValidator = new ProductValidator();
        productValidator.setSpringValidators(springValidators);
        return productValidator;
    }
}

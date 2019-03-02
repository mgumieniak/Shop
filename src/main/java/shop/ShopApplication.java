package shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shop.data.ClientRepository;
import shop.data.ProductRepository;
import shop.domens.Customer;
import shop.domens.Product;

import java.math.BigDecimal;

import static shop.domens.Product.Condition.NEW;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(ProductRepository repo, ClientRepository clientRepository) {
        return args -> {


           repo.save(new Product("iPhone 5",new BigDecimal(3000),"iPhone 8 cali",
                   "Apple","SmartPhone",30,0,false, NEW));
            repo.save(new Product("Samsung Galaxy 5",new BigDecimal(2000),"Samsung 8 cali",
                    "Samsung","SmartPhone",30,0,false, NEW));



            Customer customer = new Customer(100,"Jan","Kazimiera 17",4);
            clientRepository.save(customer);
        };
    }
}


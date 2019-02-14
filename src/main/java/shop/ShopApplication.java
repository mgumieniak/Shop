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

/*


            Product tvMsi = new Product("P1201", "MSI", new BigDecimal(600));
            tvMsi.setDescription("telewizor 23 cali");
            tvMsi.setCategory("Tv");
            tvMsi.setManufacturer("MSI");
            tvMsi.setUnitsInStock(10);*/

            repo.save(new Product(100,"iPhone 5",new BigDecimal(3000),"iPhone 8 cali",
                    "Apple","Smart Phone",30,0,false, NEW));
            repo.save(new Product(101,"Samsung Galaxy 5",new BigDecimal(2000),"Samsung 8 cali",
                    "Samsung","Smart Phone",30,0,false, NEW));




            Customer customer = new Customer(100,"Jan","Kazimiera 17",4);
            clientRepository.save(customer);
        };
    }
}


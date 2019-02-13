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

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(ProductRepository repo, ClientRepository clientRepository) {
        return args -> {
            Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
            iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym wyświetlaczem o rozdzielczości 640×1136 oraz 8-megapikselowym aparatem");
            iphone.setCategory("Smart Phone");
            iphone.setManufacturer("Apple");
            iphone.setUnitsInStock(1000);

            Product samsung = new Product("P1200", "Samsung", new BigDecimal(700));
            samsung.setDescription("Super samsung ze hej");
            samsung.setCategory("Smart Phone");
            samsung.setManufacturer("Samsung");
            samsung.setUnitsInStock(500);

            Product tvMsi = new Product("P1201", "MSI", new BigDecimal(600));
            tvMsi.setDescription("telewizor 23 cali");
            tvMsi.setCategory("Tv");
            tvMsi.setManufacturer("MSI");
            tvMsi.setUnitsInStock(10);

            repo.save(iphone);
            repo.save(samsung);
            repo.save(tvMsi);

            Customer customer = new Customer(100,"Jan","Kazimiera 17",4);
            clientRepository.save(customer);
        };
    }
}


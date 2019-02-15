package shop.data;

import org.springframework.data.repository.CrudRepository;
import shop.domens.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategory(String category);
    List<Product> findByUnitPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

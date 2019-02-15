package shop.service;

import shop.domens.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> findByCategory(String category);
    Product getProductById(long id);
    List<Product> getProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    void addProduct(Product product);
}

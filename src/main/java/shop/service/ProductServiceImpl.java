package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.data.ProductRepository;
import shop.domens.Product;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
         List<Product> productList = new ArrayList<>();
         productRepository.findAll().forEach(i->productList.add(i));

         return productList;
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<Product> getProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByUnitPriceBetween(minPrice, maxPrice);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

}

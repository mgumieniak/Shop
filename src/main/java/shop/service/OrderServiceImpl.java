package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.data.ProductRepository;
import shop.domens.Product;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void processOrder(long productId, int count) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresentOrElse(
                v->
                {
                    if(product.get().getUnitsInStock() > count){
                        product.get().setUnitsInStock(product.get().getUnitsInStock() - count);
                        productRepository.save(product.get());
                    }else
                        {
                        throw new IllegalArgumentException("Panie malo towara");
                    }

                },
                ()-> {throw new NullPointerException("Panie malo towara");}
        );
        productRepository.save(product.get());
    }
}

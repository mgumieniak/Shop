package shop.web;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import shop.data.ClientRepository;
import shop.data.ProductRepository;
import shop.data.UserRepository;
import shop.domens.Order;
import shop.domens.Product;
import shop.domens.User;
import shop.service.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.domens.Product.Condition.NEW;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ProductService productService;

    private List<Product> productcs;


   @Before
    public void setup() {

        productcs = Arrays.asList(
                new Product("iPhone 5",new BigDecimal(3000),"iPhone 8 cali",
                        "Apple","SmartPhone",30,0,false, NEW),
                new Product("Samsung Galaxy 5",new BigDecimal(2000),"Samsung 8 cali",
                        "Samsung","SmartPhone",30,0,false, NEW)
        );

        when(productRepository.findAll())
                .thenReturn(productcs);

        when(productRepository.findByCategory("SmartPhone")).
                thenReturn(productcs);

        when(productRepository.findByUnitPriceBetween(new BigDecimal(1500),new BigDecimal(2500))).
                thenReturn(Arrays.asList(new Product("Samsung Galaxy 5",new BigDecimal(2000),"Samsung 8 cali",
                        "Samsung","SmartPhone",30,0,false, NEW)));

        when(userRepository.findByUsername("testuser"))
                .thenReturn(new User("testuser","testpass"));

       when(productService.getProductById(1))
               .thenReturn(new Product("iPhone 5",new BigDecimal(3000),"iPhone 8 cali",
                       "Apple","SmartPhone",30,0,false, NEW));
    }

    @Test
    void getProductList() throws Exception{
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("productList",productcs));
    }


    @Test
    void getProductsByCategory() throws Exception{
       mockMvc.perform(get("/products/{category}","SmartPhone"))
               .andExpect(status().isOk());
    }


    @Test
    void getProductById() throws Exception{

        mockMvc.perform(get("/products?1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product"));
                //.andExpect(model().attribute("product",productService.getProductById(1)))
                //.andExpect(model().attribute("newOrder",new Order(1)));
    }

    @Test
    void getProductByPriceBetween() throws Exception{
       mockMvc.perform(get("/products/productPrice?priceMin=1500&priceMax=2500"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testuser", password="testpass", authorities="ROLE_USER")
    void getAddNewProductForm() throws Exception{
        mockMvc.perform(get("/products/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addProduct"))
                .andExpect(model().attribute("newProduct",new Product()));
    }

    @Test
    @WithMockUser(username="testuser", password="testpass", authorities="ROLE_USER")
    void processAddNewProductForm() throws Exception{

    }

    @Test
    void initialiseBinder() {
    }

    @Test
    void handleError() {
    }
}
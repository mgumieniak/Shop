package shop.web;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import shop.data.ClientRepository;
import shop.data.ProductRepository;
import shop.data.UserRepository;
import shop.domens.Order;
import shop.domens.Product;
import shop.domens.User;
import shop.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.domens.Product.Condition.NEW;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    private List<Product> productcs;
    private Product productTest;


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


       when(productRepository.findById(1L))
               .thenReturn(java.util.Optional.of(new Product("iPhone 5", new BigDecimal(3000), "iPhone 8 cali",
                       "Apple", "SmartPhone", 30, 0, false, NEW)));

       productTest = new Product("test",new BigDecimal(2000),"test description",
               "Apple","SmartPhone",20,0,false, NEW);

    }

    @Test
    void getProductList() throws Exception{
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("productList",productcs));
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

        when(productRepository.save(productTest))
                .thenReturn(productTest);

        mockMvc.perform(post("/products/add").with(csrf())
        .content("name=test&unitPrice=2000&description=test+description&manufacturer=Apple&category=SmartPhone" +
                "&unitsInStock=20&condition=NEW")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().stringValues("Location","/products"));
    }
}
package shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import shop.domens.Product;
import shop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("productsList",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/{category}")
    public String getProductsByCategory(Model model,@PathVariable("category") String productCategory){
        model.addAttribute("productsList",productService.findByCategory(productCategory));
        return "products";
    }

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @GetMapping("/productPrice")
    public String getProductByPriceBetween(@RequestParam("priceMin") BigDecimal priceMin,@RequestParam("priceMax") BigDecimal priceMax, Model model) {
        model.addAttribute("productsList", productService.getProductByPriceBetween(priceMin,priceMax));
        return "products";
    }

    @GetMapping("/add")
    public String getAddNewProductForm(Model model){
        Product newProduct = new Product();
        model.addAttribute("newProduct",newProduct);

        return "addProduct";
    }

    @PostMapping("/add")
    public String processAddNewProductForm(@ModelAttribute ("newProduct") Product newProduct, BindingResult result){

        String[] suppressedFields=result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Proba wiązania niedozwolonych pol: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder", "discontinued");

        binder.setAllowedFields("name","unitPrice","description","manufacturer","category","unitsInStock","condition");
    }
}

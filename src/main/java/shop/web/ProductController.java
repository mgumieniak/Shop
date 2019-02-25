package shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import shop.domens.Product;
import shop.exception.NoProductsFoundUnderCategoryException;
import shop.exception.ProductNotFoundException;
import shop.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;



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
        List<Product> productList = productService.findByCategory(productCategory);
        if (productList == null || productList.isEmpty()){
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("productsList",productList);
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
    public String processAddNewProductForm(@ModelAttribute ("newProduct") @Valid Product newProduct, BindingResult result){

        String[] suppressedFields=result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Proba wiązania niedozwolonych pol: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        if(result.hasErrors()){
            return "addProduct";
        }

        productService.addProduct(newProduct);
        //save file in images
        MultipartFile productImage = newProduct.getProductImage();
        if (productImage!=null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File("D:\\JAVA workspace\\Spring MVC\\shop\\src\\main\\resources\\static\\images\\"+ newProduct.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }
        return "redirect:/products";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder", "discontinued");

        binder.setAllowedFields("name","unitPrice","description","manufacturer","category","unitsInStock","condition",
                "productImage");

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }
}

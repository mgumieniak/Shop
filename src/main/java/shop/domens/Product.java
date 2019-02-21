package shop.domens;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.validator.PriceAndAmountOfProduct;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
//@RequiredArgsConstructor(access =AccessLevel.PUBLIC)
@NoArgsConstructor(access =AccessLevel.PUBLIC, force = true)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  long productId;

    @Size(min=3, max=60, message = "Invalid name. The correct name contain 3-60 characters")
    private  String name;

    @Min(value = 0, message = "The price can not be negative")
    @Digits(integer = 8,fraction = 2, message = "Invalid price format. The correct price is in format:  xxx,xx")
    @NotNull(message = "Price is required")
    private  BigDecimal unitPrice;

    @NotNull
    @Size(min=2,message = "Description is required")
    private  String description;

    @NotNull
    @Size(min=2,message = "Producer is required")
    private  String manufacturer;

    @NotNull
    @Size(min=2,message = "Category is required")
    private  String category;

    //@Min(value = 0, message = "Units in stock can not be negative")
    //@Digits(integer = 6,fraction = 0, message = "Invalid units in stock format")
    @PriceAndAmountOfProduct
    private  int unitsInStock;

    private  int unitsInOrder;
    private  boolean discontinued;
    @Enumerated(EnumType.STRING)
    private  Condition condition;
    @Transient
    private MultipartFile productImage;

    public static enum Condition{
        NEW, SECONDHAND
    }

    public Product(String name, BigDecimal unitPrice, String description, String manufacturer,
                   String category, int unitsInStock, int unitsInOrder, boolean discontinued, Condition condition) {

        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.manufacturer = manufacturer;
        this.category = category;
        this.unitsInStock = unitsInStock;
        this.unitsInOrder = unitsInOrder;
        this.discontinued = discontinued;
        this.condition = condition;
    }
}

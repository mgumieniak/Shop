package shop.domens;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
//@RequiredArgsConstructor(access =AccessLevel.PUBLIC)
@NoArgsConstructor(access =AccessLevel.PUBLIC, force = true)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  long productId;
    private  String name;
    private  BigDecimal unitPrice;
    private  String description;
    private  String manufacturer;
    private  String category;
    private  int unitsInStock;
    private  int unitsInOrder;
    private  boolean discontinued;
    @Enumerated(EnumType.STRING)
    private  Condition condition;
    @Lob
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

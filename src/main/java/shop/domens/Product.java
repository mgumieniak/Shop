package shop.domens;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
//@RequiredArgsConstructor(access =AccessLevel.PUBLIC)
@NoArgsConstructor(access =AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long productId;

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

    public static enum Condition{
        NEW, SECONDHAND
    }

}

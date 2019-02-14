package shop.domens;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor(access =AccessLevel.PUBLIC)
@NoArgsConstructor(access =AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long productId;
    @NonNull
    private  String name;
    @NonNull
    private  BigDecimal unitPrice;
    @NonNull
    private  String description;
    @NonNull
    private  String manufacturer;
    @NonNull
    private  String category;
    @NonNull
    private  int unitsInStock;
    @NonNull
    private  int unitsInOrder;
    @NonNull
    private  boolean discontinued;
    @Enumerated(EnumType.STRING)
    private  Condition condition;



    public static enum Condition{
        NEW, SECONDHAND
    }

}

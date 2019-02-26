package shop.domens;

import lombok.Data;

@Data
public class Order {
    private final long id;
    private int orderSize;
}

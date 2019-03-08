package shop.exception;

import lombok.Data;


@Data
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    private final long productId;

}

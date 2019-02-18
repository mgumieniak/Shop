package shop.exception;


import java.util.function.Supplier;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    private long productId;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(long productId) {
        super();
        this.productId=productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}

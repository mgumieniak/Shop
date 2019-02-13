package shop.domens;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {

    @Id
    private String productId;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String manufacturer;
    private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discontinued;
    private Condition condition;

    public static enum Condition{
        NEW, SECONDHAND
    }
    public Product() {
        super();
    }

    public Product(String productId, String name, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }


    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category='" + category + '\'' +
                ", unitsInStock=" + unitsInStock +
                ", unitsInOrder=" + unitsInOrder +
                ", discontinued=" + discontinued +
                ", condition='" + condition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getUnitsInStock() == product.getUnitsInStock() &&
                getUnitsInOrder() == product.getUnitsInOrder() &&
                isDiscontinued() == product.isDiscontinued() &&
                Objects.equals(getProductId(), product.getProductId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getUnitPrice(), product.getUnitPrice()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getManufacturer(), product.getManufacturer()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getCondition(), product.getCondition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getUnitPrice(), getDescription(), getManufacturer(), getCategory(), getUnitsInStock(), getUnitsInOrder(), isDiscontinued(), getCondition());
    }
}

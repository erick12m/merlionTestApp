package merliontechs.domain.model;

import merliontechs.domain.Product;

import java.math.BigDecimal;

public class MProduct {

    private Long id;
    private String name;
    private BigDecimal price;

    public MProduct() {

    }

    public MProduct(Product producto){
        this.id = producto.getId();
        this.name = producto.getName();
        this.price = producto.getPrice();
    }

    public MProduct(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

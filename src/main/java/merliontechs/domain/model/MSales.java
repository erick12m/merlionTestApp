package merliontechs.domain.model;

import merliontechs.domain.Product;
import merliontechs.domain.Sales;
import merliontechs.domain.enumeration.State;

import java.time.LocalDate;

public class MSales {

    private Long id;
    private State state;
    private LocalDate date;
    private Product producto;

    public MSales(Long id, State state, LocalDate date, Product producto) {
        this.id = id;
        this.state = state;
        this.date = date;
        this.producto = producto;
    }

    public MSales() {
    }

    public MSales(Sales sales) {
        this.id = sales.getId();
        this.state = sales.getState();
        this.date = sales.getDate();
        this.producto = sales.getProduct();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }
}

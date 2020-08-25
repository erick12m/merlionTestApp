package merliontechs.domain.model;

import java.math.BigDecimal;

public class ProductoVendido {


    private String name;
    private int cantidadVentas;
    private BigDecimal ingresos;

    public ProductoVendido(String name, BigDecimal ingreso) {
        this.name = name;
        this.cantidadVentas = 1;
        this.ingresos = ingreso;
    }

    public String getName() {
        return name;
    }

    public int getVentas() {
        return cantidadVentas;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void aumentarCantidadVentas() {
        this.cantidadVentas += 1;

    }

    public void aumentarIngresos(BigDecimal price) {
        this.ingresos = this.ingresos.add(price);
    }
}

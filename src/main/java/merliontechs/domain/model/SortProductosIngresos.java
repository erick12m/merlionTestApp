package merliontechs.domain.model;

import java.util.Comparator;

public class SortProductosIngresos implements Comparator<ProductoVendido> {
    @Override
    public int compare(ProductoVendido producto1, ProductoVendido producto2) {
        return (producto2.getIngresos().subtract(producto1.getIngresos()).toBigInteger().intValueExact());
    }
}

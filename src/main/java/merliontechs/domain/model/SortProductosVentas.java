package merliontechs.domain.model;

import java.util.Comparator;

public class SortProductosVentas implements Comparator<ProductoVendido> {
    @Override
    public int compare(ProductoVendido producto1, ProductoVendido producto2) {
        return (producto2.getVentas() - producto1.getVentas());
    }
}

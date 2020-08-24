package merliontechs.domain.model;

import java.util.Comparator;

public class SortPorFecha implements Comparator<SalesPorDia> {
    @Override
    public int compare(SalesPorDia venta1, SalesPorDia venta2) {
        return (venta1.getFecha().getDayOfYear() - venta2.getFecha().getDayOfYear());
    }
}

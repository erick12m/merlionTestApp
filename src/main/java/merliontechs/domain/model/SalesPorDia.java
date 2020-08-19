package merliontechs.domain.model;

import java.time.LocalDate;

public class SalesPorDia {

    LocalDate fecha;
    Integer cantidadVentas;

    public SalesPorDia(LocalDate fecha) {
        this.fecha = fecha;
        cantidadVentas = 1;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Integer getCantidadVentas() {
        return cantidadVentas;
    }

    public void aumentarCantidadVentas() {
        this.cantidadVentas += 1;
    }

}

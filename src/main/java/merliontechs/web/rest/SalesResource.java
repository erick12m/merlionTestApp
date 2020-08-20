package merliontechs.web.rest;

import merliontechs.domain.Product;
import merliontechs.domain.Sales;
import merliontechs.domain.model.ProductoVendido;
import merliontechs.domain.model.SalesPorDia;
import merliontechs.domain.model.SortProductosIngresos;
import merliontechs.domain.model.SortProductosVentas;
import merliontechs.repository.SalesRepository;
import merliontechs.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

import merliontechs.domain.enumeration.State;
/**
 * REST controller for managing {@link merliontechs.domain.Sales}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SalesResource {

    private final Logger log = LoggerFactory.getLogger(SalesResource.class);

    private static final String ENTITY_NAME = "sales";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesRepository salesRepository;



    public SalesResource(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    /**
     * {@code POST  /sales} : Create a new sales.
     *
     * @param sales the sales to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sales, or with status {@code 400 (Bad Request)} if the sales has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales")
    public ResponseEntity<Sales> createSales(@RequestBody Sales sales) throws URISyntaxException {
        log.debug("REST request to save Sales : {}", sales);
        if (sales.getId() != null) {
            throw new BadRequestAlertException("A new sales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sales result = salesRepository.save(sales);
        return ResponseEntity.created(new URI("/api/sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales} : Updates an existing sales.
     *
     * @param sales the sales to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sales,
     * or with status {@code 400 (Bad Request)} if the sales is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sales couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales")
    public ResponseEntity<Sales> updateSales(@RequestBody Sales sales) throws URISyntaxException {
        log.debug("REST request to update Sales : {}", sales);
        if (sales.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sales result = salesRepository.save(sales);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sales.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sales} : get all the sales.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sales in body.
     */
    @GetMapping("/sales")
    public List<Sales> getAllSales() {
        log.debug("REST request to get all Sales");
        return salesRepository.findAll();
    }

    /**
     * {@code GET  /sales/:id} : get the "id" sales.
     *
     * @param id the id of the sales to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sales, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales/{id}")
    public ResponseEntity<Sales> getSales(@PathVariable Long id) {
        log.debug("REST request to get Sales : {}", id);
        Optional<Sales> sales = salesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sales);
    }

    /**
     * {@code DELETE  /sales/:id} : delete the "id" sales.
     *
     * @param id the id of the sales to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable Long id) {
        log.debug("REST request to delete Sales : {}", id);
        salesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/sales/all")
    public List<SalesPorDia> obtenerVentasPorDia() {
        List<Sales> ventas = getAllSales();
        Map<LocalDate, SalesPorDia> dicVentas = new HashMap<LocalDate,SalesPorDia>();
        for (Sales venta: ventas){
           LocalDate fecha = venta.getDate();
            ingresarAlDiccionario(dicVentas, fecha);
        }
        return (new ArrayList<>(dicVentas.values()));
    }

    @GetMapping("/sales/delivered")
    public List<SalesPorDia> obtenerVentasDeliveredPorDia() {
        List<Sales> ventas = getAllSales();
        Map<LocalDate, SalesPorDia> dicVentas = new HashMap<LocalDate,SalesPorDia>();
        for (Sales venta: ventas){
           LocalDate fecha = venta.getDate();
           State estado = venta.getState();
           if (estado == State.DELIVERED){
               ingresarAlDiccionario(dicVentas, fecha);
           }
        }
        return (new ArrayList<>(dicVentas.values()));
    }

    @GetMapping("/sales/more-selled")
    public List<ProductoVendido> obtenerProductosMasVendidos() {
        List<ProductoVendido> listaProductosVendidos = getListaProductoVendidos();
        listaProductosVendidos.sort(new SortProductosVentas());
        return getTop5Productos(listaProductosVendidos);
    }

    @GetMapping("/sales/more-entry")
    public List<ProductoVendido> obtenerProductosConMasIngreso() {
        List<ProductoVendido> listaProductosVendidos = getListaProductoVendidos();
        listaProductosVendidos.sort(new SortProductosIngresos());
        return getTop5Productos(listaProductosVendidos);
    }

    private void ingresarAlDiccionario(Map<LocalDate, SalesPorDia> dicVentas, LocalDate fecha) {
        if (!dicVentas.containsKey(fecha)) {
            dicVentas.put(fecha, new SalesPorDia(fecha));
        } else {
            SalesPorDia salesActual = dicVentas.get(fecha);
            salesActual.aumentarCantidadVentas();
            dicVentas.put(fecha, salesActual);
        }
    }

    private List<ProductoVendido> getTop5Productos(List<ProductoVendido> listaProductosVendidos) {
        List<ProductoVendido> top5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            top5.add(listaProductosVendidos.get(i));
        }
        return top5;
    }

    private List<ProductoVendido> getListaProductoVendidos() {
        List<Sales> ventas = getAllSales();
        Map<Long, ProductoVendido> dicProductos = new HashMap<Long, ProductoVendido>();
        for (Sales venta: ventas){
            Product producto = venta.getProduct();
            Long id = producto.getId();
            if (!dicProductos.containsKey(id)){
                dicProductos.put(id, new ProductoVendido(producto.getName(), producto.getPrice()));
            }
            else {
                ProductoVendido productoActual = dicProductos.get(id);
                productoActual.aumentarCantidadVentas();
                productoActual.aumentarIngresos(producto.getPrice());
                dicProductos.put(id, productoActual);
            }
        }
        List<ProductoVendido> listaProductosVendidos = new ArrayList<>(dicProductos.values());
        return listaProductosVendidos;
    }
}

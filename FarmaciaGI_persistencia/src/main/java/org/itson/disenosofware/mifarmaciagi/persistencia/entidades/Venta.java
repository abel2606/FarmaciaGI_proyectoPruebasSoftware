package org.itson.disenosofware.mifarmaciagi.persistencia.entidades;

import java.time.Instant;
import java.util.List;

public class Venta {

    private String codigo; // codigo de la venta
    private List<Producto> productos; //Lista de productos de la venta
    private List<Promocion> promociones; //Lista de promociones de la venta
    private Float costo_total; //Costo total de la venta
    private Instant fecha;

    /**
     * Constructor vacio
     */
    public Venta() {
    }

    /**
     * Obtiene el código d la venta
     *
     * @return código de la venta
     */
    public String getCodigoVenta() {
        return codigo;
    }

    /**
     * Establece el código de la venta
     *
     * @param codigo_venta
     */
    public void setCodigoVenta(String codigo_venta) {
        this.codigo = codigo_venta;
    }

    /**
     * Obtiene la lista de productos de la venta
     *
     * @return productos de la venta
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos de la venta
     *
     * @param productos productos de la venta
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene las promociones de la venta
     *
     * @return lsita de promociones de la venta
     */
    public List<Promocion> getPromociones() {
        return promociones;
    }

    /**
     * Establece la lista de promociones de la venta
     *
     * @param promociones promociones de la venta
     */
    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    /**
     * Obtiene el costo total de la venta
     *
     * @return costo total de venta
     */
    public Float getCostoTotal() {
        return costo_total;
    }

    /**
     * Modifica el costo total de la venta
     *
     * @param costo_total costo total de la venta
     */
    public void setCostoTotal(Float costo_total) {
        this.costo_total = costo_total;
    }

    /**
     * Obtiene la fecha de la venta
     *
     * @return fecha de venta
     */
    public Instant getFechaVenta() {
        return fecha;
    }

    /**
     * Establece la fecha de la venta
     *
     * @param fecha_venta fecha de la venta
     */
    public void setFechaVenta(Instant fecha_venta) {
        this.fecha = fecha_venta;
    }

}

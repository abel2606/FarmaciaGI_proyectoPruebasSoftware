package org.itson.disenosoftware.farmaciagi_dtos;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class VentaDTO {

    private String codigo;
    private List<ProductoDTO> productos;
    private List<PromocionDTO> promociones;
    private Float total;
    private Calendar fecha;

    /**
     * Constructor que recibe el código de la venta.
     *
     * @param codigo El código de la venta
     */
    public VentaDTO(String codigo) {
        this.codigo = codigo;
        this.productos = new LinkedList<>();
        this.total = 0.0F;
        this.fecha = null;
    }

    /**
     * Constructor que recibe el código, la lista de productos, el total y la
     * fecha de la venta.
     *
     * @param codigo El código de la venta
     * @param productos La lista de productos de la venta
     * @param total El total de la venta
     * @param fecha La fecha de la venta
     */
    public VentaDTO(String codigo, List<ProductoDTO> productos, Float total, Calendar fecha) {
        this.codigo = codigo;
        this.productos = productos;
        this.total = total;
        this.fecha = fecha;
    }

    /**
     * Constructor que recibe el código, la lista de productos, el total y la
     * fecha de la venta.
     *
     * @param codigo El código de la venta
     * @param productos La lista de productos de la venta
     * @param promociones La lista de promociones
     * @param total El total de la venta
     * @param fecha La fecha de la venta
     */
    public VentaDTO(String codigo, List<ProductoDTO> productos, List<PromocionDTO> promociones, Float total, Calendar fecha) {
        this.codigo = codigo;
        this.productos = productos;
        this.promociones = promociones;
        this.total = total;
        this.fecha = fecha;
    }

    /**
     * Permite obtener el código de la venta.
     *
     * @return El código de la venta
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Permite obtener la lista de productos de la venta.
     *
     * @return La lista de productos de la venta
     */
    public List<ProductoDTO> getProductos() {
        return productos;
    }

    /**
     * Permite obtener la lista de promociones de la venta.
     *
     * @return La lista de promociones de la venta
     */
    public List<PromocionDTO> getPromociones() {
        return promociones;
    }

    /**
     * Permite establecer la lista de promociones de la venta.
     *
     * @param promociones La lista de promociones de la venta
     */
    public void setPromociones(List<PromocionDTO> promociones) {
        this.promociones = promociones;
    }

    /**
     * Permite obtener el total de la venta.
     *
     * @return El total de la venta
     */
    public Float getTotal() {
        return total;
    }

    /**
     * Permite obtener la fecha de la venta.
     *
     * @return La fecha de la venta
     */
    public Calendar getFecha() {
        return fecha;
    }

    /**
     * Permite saber si la venta es válida o no.
     *
     * @return true si la venta es válida, false en caso contrario
     */
    public boolean isValid() {
        if (total < 0) {
            return false;
        }
        if (fecha.after(new GregorianCalendar())) {
            return false;
        }
        return true;
    }

    /**
     * Permite obtener un código hash de la venta.
     *
     * @return El código hash de la venta
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     * Permite saber si dos ventas son iguales.
     *
     * @param obj La venta con la que se comparará
     * @return true si se trata de la misma venta, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VentaDTO other = (VentaDTO) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}

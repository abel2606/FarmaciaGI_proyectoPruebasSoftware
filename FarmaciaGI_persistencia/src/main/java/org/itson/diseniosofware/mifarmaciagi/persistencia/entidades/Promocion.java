package org.itson.diseniosofware.mifarmaciagi.persistencia.entidades;

import java.util.Objects;

public class Promocion {

    private String codigo;
    private String descripcion;
    private Producto producto;
    private Integer cantidad;
    private Float precioUnitario;

    /**
     * Constructor vacío.
     */
    public Promocion() {
    }

    /**
     * Constructor que recibe el código de la promoción.
     *
     * @param codigo El código de la promoción
     */
    public Promocion(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Constructor que recibe el código, la descripción, el producto, la
     * cantidad mínima de producto, y el precio untario del producto de la
     * promoción.
     *
     * @param codigo El código de la promoción
     * @param descripcion La descripción de la promoción
     * @param producto El producto de la promoción
     * @param cantidad La cantidad mínima de producto de la promoción
     * @param precioUnitario El precio unitario del producto de la promoción
     */
    public Promocion(String codigo, String descripcion, Producto producto, Integer cantidad, Float precioUnitario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Permite obtener el código de la promoción.
     *
     * @return El código de la promoción
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Permite establecer el código de la promoción.
     *
     * @param codigo El código de la promoción
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Permite obtener la descripción de la promoción.
     *
     * @return La descripción de la promoción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Permite establecer la descripción de la promoción.
     *
     * @param descripcion La descripción de la promoción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Permite obtener el producto de la promoción.
     *
     * @return El producto de la promoción
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Permite establecer el producto de la promoción.
     *
     * @param producto El producto de la promoción
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Permite obtener la cantidad mínima de producto para aplicar la promoción.
     *
     * @return La cantidad mínima de producto para aplicar la promoción
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Permite establecer la cantidad mínima de producto para aplicar la
     * promoción.
     *
     * @param cantidad L cantidad mínima de producto para aplicar la promoción
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Permite obtener el precio unitario del producto de la promoción una vez
     * que esta se aplique.
     *
     * @return El precio unitario del producto de la promoción
     */
    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Permite establecer el precio unitario del producto de la promoción una
     * vez que esta se aplique.
     *
     * @param precioUnitario El precio unitario del producto de la promoción
     */
    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Permite obtener el código hash de la promoción.
     *
     * @return El código hash de la promoción
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     * Permite determinar si dos promociones son iguales o no.
     *
     * @param obj La promoción con la que se comparará esta promoción
     * @return true si se trata de la misma promoción, false en caso contrario
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
        final Promocion other = (Promocion) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    /**
     * Permite obtener una cadena con los atributos de la promoción.
     *
     * @return Una cadena con los atributos de la promoción
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Promocion{");
        sb.append("codigo=").append(codigo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", producto=").append(producto);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precioUnitario=").append(precioUnitario);
        sb.append('}');
        return sb.toString();
    }

}

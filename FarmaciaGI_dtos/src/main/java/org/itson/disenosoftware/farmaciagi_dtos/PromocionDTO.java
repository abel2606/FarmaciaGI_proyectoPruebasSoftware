package org.itson.disenosoftware.farmaciagi_dtos;

import java.util.Objects;

public class PromocionDTO {

    private String codigo;
    private String descripcion;
    private ProductoDTO producto;
    private Integer cantidad;
    private Float precioUnitario;

    /**
     * Constructor que recibe el código de la promoción.
     *
     * @param codigo El código de la promoción.
     */
    public PromocionDTO(String codigo) {
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
    public PromocionDTO(String codigo, String descripcion, ProductoDTO producto, Integer cantidad, Float precioUnitario) {
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
     * Permite obtener la descripción de la promoción.
     *
     * @return La descripción de la promoción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Permite obtener el producto de la promoción.
     *
     * @return El producto de la promoción
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Permite obtener la cantidad mínima de producto para que se aplique la
     * promoción.
     *
     * @return La cantidad mínima de producto de la promoción.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Permite obtener el precio unitario del producto una vez que se aplique la
     * promoción.
     *
     * @return El precion unitario del producto de la promoción
     */
    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Permite saber si la promoción es válida.
     *
     * @return true si la promoción es válida, false en caso contrario
     */
    public boolean isValid() {
        if (descripcion.isBlank()) {
            return false;
        }
        if (precioUnitario < 0) {
            return false;
        }
        if (precioUnitario.isNaN()) {
            return false;
        }
        if (producto == null) {
            return false;
        }
        if (cantidad < 1) {
            return false;
        }
        return true;
    }

    /**
     * Permite obtener un código hash que representa esta promoción.
     *
     * @return El código hash de la promocion
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     * Permite comparar una promoción con otra y saber si son la misma
     * promoción.
     *
     * @param obj La promoción con la que se comparará
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
        final PromocionDTO other = (PromocionDTO) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return descripcion + ", " + producto;
    }

}

package org.itson.disenosoftware.farmaciagi_dtos;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Objects;

public class ProductoDTO {

    private String codigo;
    private String nombre;
    private Float costo;
    private String marca;
    private Integer cantidad;
    private LinkedList<String> id_proveedores;

    /**
     * Constructor.
     */
    public ProductoDTO() {
    }

    /**
     * Constructor que recibe el código, nombre y costo del producto.
     *
     * @param codigo El código del producto
     * @param nombre El nombre del producto
     * @param costo El costo del producto
     */
    public ProductoDTO(String codigo, String nombre, Float costo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
    }

    /**
     * Constructor que recibe el código, nombre, costro, marca y cantidad del
     * producto.
     *
     * @param codigo El código del producto
     * @param nombre El nombre del producto
     * @param costo El costo del producto
     * @param marca La marca del producto
     * @param cantidad La cantidad del producto
     */
    public ProductoDTO(String codigo, String nombre, Float costo, String marca, Integer cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    /**
     * Constructor que recibe el código, nombre, costro y marca del producto.
     *
     * @param codigo El código del producto
     * @param nombre El nombre del producto
     * @param costo El costo del producto
     * @param marca La marca del producto
     */
    public ProductoDTO(String codigo, String nombre, Float costo, String marca) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.marca = marca;
    }

    /**
     * Permite obtener el código del producto.
     *
     * @return El código del producto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Permite establecer el código del producto
     *
     * @param codigo El código del producto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Permite obtener el nombre del producto.
     *
     * @return El nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer el nombre del producto.
     *
     * @param nombre El nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el costo del producto.
     *
     * @return El costo del producto
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Permite establecer el costo del producto.
     *
     * @param costo El costo del producto
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Permite obtener la marca del producto.
     *
     * @return La marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Permite establecer la marca del producto.
     *
     * @param marca La marca del producto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Permite obtener la cantidad del producto.
     *
     * @return La cantidad del producto
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Permite establecer la cantidad del producto.
     *
     * @param cantidad La cantidad del producto
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LinkedList<String> getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(LinkedList<String> id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    /**
     * Permite sabe si el producto es válido o no.
     *
     * @return true si el producto es válido, false en caso contrario
     */
    public boolean isValid() {
        if (cantidad < 0) {
            return false;
        }
        if (costo < 0.0) {
            return false;
        }
        return true;
    }

    /**
     * Permite obtener un código hash que representa este producto.
     *
     * @return El código hash del producto
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
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
        final ProductoDTO other = (ProductoDTO) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return nombre + ", " + marca + ", " + NumberFormat.getCurrencyInstance().format(costo);
    }

}

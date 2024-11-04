/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.diseniosofware.mifarmaciagi.persistencia.entidades;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author Enrique Rodriguez
 */
public class Compra {

    private ObjectId _id;
    private String codigo; 
    private Producto producto;
    private Proveedor proveedor;
    private Float costoTotal;
    private Instant fecha;

    public Compra() {
    }

    public Compra(String codigo, Producto producto, Proveedor proveedor, Float costoTotal, Instant fecha) {
        this.codigo = codigo;
        this.producto = producto;
        this.proveedor = proveedor;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
    }

    /**
     * Método para obtener el código
     * 
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Método para establecer el código
     * 
     * @param codigo codigo del la compra
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    /**
     * Método para obtener el producto
     * 
     * @return producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Método para obtener el código
     * 
     * @param producto producto
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Método para obtener el proveedor
     * 
     * @return proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Método para establecer el proveedor
     * 
     * @param proveedor rpoveedor
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Método para obtener el costo total
     * 
     * @return 
     */
    public Float getCostoTotal() {
        return costoTotal;
    }

    /**
     * Método para establecer el costo total
     * 
     * @param costoTotal
     */
    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * Método para obtener la fecha
     * 
     * @return fecha
     */
    public Instant getFecha() {
        return fecha;
    }

    /**
     * Método para establecer la fecha
     * 
     * @param fecha fecha
     */
    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    /**
     * Método toString que devuelve una cadena de texto conformada por todos los
     * atributos de la clase.
     * 
     * @return Cadena de texto
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra{");
        sb.append("_id=").append(_id);
        sb.append(", productos=").append(producto);
        sb.append(", proveedores=").append(proveedor);
        sb.append(", costoTotal=").append(costoTotal);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }
    
    /**
     * Hashcode para la comparacion de objetos creados de esta clase entidad
     * 
     * @return valor hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this._id);
        hash = 43 * hash + Objects.hashCode(this.producto);
        hash = 43 * hash + Objects.hashCode(this.proveedor);
        hash = 43 * hash + Objects.hashCode(this.costoTotal);
        hash = 43 * hash + Objects.hashCode(this.fecha);
        return hash;
    }
    
    /**
     * Método equals para la comparación.
     * @param obj objeto
     * @return true o false, dependiendo el caso
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
        final Compra other = (Compra) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        if (!Objects.equals(this.proveedor, other.proveedor)) {
            return false;
        }
        if (!Objects.equals(this.costoTotal, other.costoTotal)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }
    
    
    
}

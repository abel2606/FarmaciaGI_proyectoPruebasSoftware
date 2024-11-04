/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.disenosoftware.farmaciagi_dtos;

import java.util.Calendar;

/**
 * Clase para envio de datos referente a compras (DTO)
 * 
 * @author Enrique Rodriguez
 */
public class CompraDTO {
    private String codigo; 
    private ProductoDTO producto;
    private ProveedorDTO proveedor;
    private Float costoTotal;
    private Calendar fecha;
    
    /**
     * Constructor vacío
     */
    public CompraDTO() {
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
    
    /**
     * Método para obtener el producto
     * 
     * @return producto
     */
    public ProductoDTO getProducto() {
        return producto;
    }
    
    /**
     * Método para obtener el código
     * 
     * @param producto producto
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
    
    /**
     * Método para obtener el proveedor
     * 
     * @return proveedor
     */
    public ProveedorDTO getProveedor() {
        return proveedor;
    }
    
    /**
     * Método para establecer el proveedor
     * 
     * @param proveedor rpoveedor
     */
    public void setProveedores(ProveedorDTO proveedor) {
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
    public Calendar getFecha() {
        return fecha;
    }
    
    /**
     * Método para establecer la fecha
     * 
     * @param fecha fecha
     */
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
    // Contructores
    
    /**
     * Método constructor que inicializa los atributos de la clase a los
     * valores de los párametros.
     * 
     * @param codigo codigo
     * @param producto producto
     * @param proveedor proveedor
     * @param costoTotal costo total
     * @param fecha fecha
     */
    public CompraDTO(String codigo, ProductoDTO producto, ProveedorDTO proveedor, Float costoTotal, Calendar fecha) {
        this.codigo = codigo;
        this.producto = producto;
        this.proveedor = proveedor;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
    }
    
    /**
     * Constructor que inicializa el codigo al valor de su párametro
     * 
     * @param codigo código
     */
    public CompraDTO(String codigo) {
        this.codigo = codigo;
    }
    
    
}

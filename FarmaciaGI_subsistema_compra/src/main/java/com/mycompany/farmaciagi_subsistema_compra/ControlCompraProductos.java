/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciagi_subsistema_compra;

import com.mycompany.farmaciagi_subsistema_compra_excepciones.ControlCompraProductosException;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.CompraBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.ICompraBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

/**
 * Clase control para el registro de compras.
 * 
 * @author Enrique Rodriguez
 */
public class ControlCompraProductos {
    private ICompraBO compra;
    
    /**
     * Constructor de la clase que inicializa el atributo de la clase
     * 
     */
    public ControlCompraProductos() {
        compra = new CompraBO();
    }
    
    /**
     * Método que permite registrar una compra haciendo llamado al método de
     * la clase de negocio.
     * 
     * @param compraNueva compra a registrar.
     * @throws ControlCompraProductosException en caso de no poder registrarla.
     */
    public void registrarCompra(CompraDTO compraNueva) throws ControlCompraProductosException {
        try {
            compra.registrarCompra(compraNueva);
        } catch (ObjetosNegocioException ex) {
            throw new ControlCompraProductosException("No se pudo registrar la venta.");
        }
    }
    
    /**
     * Método para encontrar una serie de proveedores que estén ligados a un 
     * producto en cuestión.
     * 
     * @param productoNuevo producto a evaluar.
     * @return lista de proveedores
     * @throws ControlCompraProductosException en caso de no poder encontrar a 
     * ningun proveedor.
     */
    public List<ProveedorDTO> encontrarProveedores(ProductoDTO productoNuevo) throws ControlCompraProductosException{
        try {
            List<ProveedorDTO> proveedores = compra.encontrarProveedores(productoNuevo);
            return proveedores;
        } catch (ObjetosNegocioException e) {
            throw new ControlCompraProductosException("No se pudo encontrar a ningun proveedor.");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmaciagi_subsistema_compra;

import com.mycompany.farmaciagi_subsistema_compra_excepciones.ControlCompraProductosException;
import com.mycompany.farmaciagi_subsistema_compra_excepciones.GestorCompraProductosException;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;

/**
 * Clase que administra la compra de productos.
 * @author Enrique Rodriguez
 */
public class GestorCompraProductos implements IGestorCompraProductos{

    private ControlCompraProductos control;
    
    /**
     * Constructor de la clase que inicializa el atributo de la clase
     * 
     */
    public GestorCompraProductos() {
        control = new ControlCompraProductos();
    }
    
    /**
     * Método para registrar una compra en una base de datos.
     * 
     * @param compraNueva compra a registrar
     * @throws GestorCompraProductosException en caso de fallar al registrar la
     * compra
     */
    @Override
    public void registrarCompra(CompraDTO compraNueva) throws GestorCompraProductosException {
        try {
            control.registrarCompra(compraNueva);
        } catch (ControlCompraProductosException ex) {
            throw new GestorCompraProductosException("No se pudo registrar la compra.");
        }
    }
    
    /**
     * Método para encontrar una lista de proveedores que están asociados a un
     * producto.
     * 
     * @param productoDTO producto para evaluación
     * @return lista de proveedores
     * @throws GestorCompraProductosException en caso de no poder devolver esta
     * lista de proveedores
     */
    @Override
    public List<ProveedorDTO> encontrarProveedores(ProductoDTO productoDTO) throws GestorCompraProductosException{
        try {
            List<ProveedorDTO> proveedores = control.encontrarProveedores(productoDTO);
            return proveedores;
        } catch (ControlCompraProductosException e) {
            throw new GestorCompraProductosException("No se pudo encontrar a ningun proveedor");
        }
    }
}

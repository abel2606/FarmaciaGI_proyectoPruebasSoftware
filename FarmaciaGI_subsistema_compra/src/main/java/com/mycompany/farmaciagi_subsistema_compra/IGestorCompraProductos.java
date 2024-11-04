/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.farmaciagi_subsistema_compra;

import com.mycompany.farmaciagi_subsistema_compra_excepciones.GestorCompraProductosException;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;

/**
 * Interfaz encargada de administrar los métodos para la compra.
 * 
 * @author Enrique Rodriguez
 */
public interface IGestorCompraProductos {
    /**
     * Método para registrar una compra en una base de datos.
     * 
     * @param compraNueva compra a registrar
     * @throws GestorCompraProductosException en caso de fallar al registrar la
     * compra
     */
    public void registrarCompra(CompraDTO compraNueva) throws GestorCompraProductosException;
    
    /**
     * Método para encontrar una lista de proveedores que están asociados a un
     * producto.
     * 
     * @param productoDTO producto para evaluación
     * @return lista de proveedores
     * @throws GestorCompraProductosException en caso de no poder devolver esta
     * lista de proveedores
     */
    public List<ProveedorDTO> encontrarProveedores(ProductoDTO productoDTO) throws GestorCompraProductosException;
}

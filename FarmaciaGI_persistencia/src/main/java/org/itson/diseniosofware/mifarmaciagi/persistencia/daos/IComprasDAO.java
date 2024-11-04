/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import java.util.List;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Compra;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;

/**
 *
 * @author Enrique Rodriguez
 */
public interface IComprasDAO {
    
    /**
     * Método el cual nos permitirá registrar una compra de productos, a una bd
     *
     * @param compra compra a registrar
     * @return compra registrada
     * @throws PersistenciaException en caso de no poder registrarla
     */
    public Compra registrarCompra(Compra compra) throws PersistenciaException;
    
    /**
     * Este método se encarga de encontrar ua venta en la bd
     *
     * @param codigo código de la venta que se desea encontrar
     * @return retorna la venta que se encontro
     */
    public Compra encontrarCompra(String codigo);
    
    /**
     * Método para encontrar a los proveedores que le pertenecen a la compra de
     * un producto.
     *
     * @param producto Producto que referencia proveedores.
     * @return lista de proveedores
     */
    public List<Proveedor>encontrarProveedores(Producto producto) throws PersistenciaException;
}

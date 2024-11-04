/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

/**
 * Interfaz para gestionar los objetos negocio que corresponden a una compra
 * 
 * @author Enrique Rodriguez
 */
public interface ICompraBO {

    /**
     * Método para registrar una compra
     *
     * @param compraNueva compra a registrar.
     *
     * @throws ObjetosNegocioException En caso de no poder agregar
     */
    public void registrarCompra(CompraDTO compraNueva) throws ObjetosNegocioException;

    /**
     * Método para encontrar una serie de proveedores asociados a un producto
     *
     * @param productoDTO producto a evaluar.
     * @return lista de proveedores
     * @throws ObjetosNegocioException
     */
    public List<ProveedorDTO> encontrarProveedores(ProductoDTO productoDTO) throws ObjetosNegocioException;
}

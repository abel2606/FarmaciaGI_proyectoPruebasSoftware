package org.itson.disenosoftware.farmaciagi_subsistema_productos;

import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.Validaciones.ExpresionesRegulares;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.excepciones.ControlProductosException;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.excepciones.GestorProductosException;

public class GestorProductos implements IGestorProductos {

    private ControlGestorProductos control;

    /**
     * Constructor.
     */
    public GestorProductos() {
        control = new ControlGestorProductos();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ProductoDTO obtenerProducto(ProductoDTO productoBuscado) {
        return control.obtenerProducto(productoBuscado);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void modCantidadProducto(ProductoDTO productoActualizado) throws GestorProductosException {
        try {
            control.modCantidadProducto(productoActualizado);
        } catch (ControlProductosException ex) {
            throw new GestorProductosException("No se pudo actualizar el producto.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
        return control.buscarProductosPorNombre(nombre);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void asignarProveedorAProducto(ProductoDTO productoDTO, ProveedorDTO proveedorDTO) throws GestorProductosException {
        try {
            control.asignarProveedorAProducto(productoDTO, proveedorDTO);
        } catch (ControlProductosException ex) {
            throw new GestorProductosException(ex.getMessage());
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) throws GestorProductosException {
        productoDTO.setCodigo(productoDTO.getCodigo().toUpperCase());
        if (ExpresionesRegulares.validarCodigo(productoDTO.getCodigo())) {
            try {
                return control.registrarProducto(productoDTO);
            } catch (ControlProductosException ex) {
                throw new GestorProductosException(ex.getMessage());
            }
        } else {
            throw new GestorProductosException("Error: el código debe estar en formato AAA-###");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ProductoDTO> obtnerInventario() {
        return control.obtenerInventario();
    }

    /**
     * Métod para actualizar un producto del registro
     * @param producto producto a actualizar
     * @throws GestorProductosException en caso de algun error
     */
    @Override
    public void actualizarProducto(ProductoDTO producto) throws GestorProductosException {
        try {
            control.actualizarProducto(producto);
        } catch (ObjetosNegocioException ex) {
            throw new GestorProductosException(ex.getMessage());
        }

    }

}

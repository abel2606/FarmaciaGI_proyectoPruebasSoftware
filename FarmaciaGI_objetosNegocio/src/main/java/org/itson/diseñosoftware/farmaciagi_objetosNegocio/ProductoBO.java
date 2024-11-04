package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.util.LinkedList;
import java.util.List;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class ProductoBO implements IProductoBO {

    private IProductosDAO productosDAO;

    /**
     * Constructor.
     */
    public ProductoBO() {
        IConexion conexion = new Conexion();
        productosDAO = new ProductosDAO(conexion);
    }

    /**
     * Este método obtiene un producto de tipo DTO del inventario por medio del
     * código único de un producto
     *
     * @param productoBuscado producto que se desea buscar
     * @return producto que se encontró
     */
    @Override
    public ProductoDTO obtenerProducto(ProductoDTO productoBuscado) {
        Producto productoInventario = new Producto();

        productoInventario.setCodigo(productoBuscado.getCodigo());

        Producto productoEncontrado = productosDAO.obtenerProducto(productoInventario);

        if (productoEncontrado != null) {

            productoBuscado.setCantidad(productoEncontrado.getCantidad());
            productoBuscado.setNombre(productoEncontrado.getNombre());
            productoBuscado.setMarca(productoEncontrado.getMarca());
            productoBuscado.setCosto(productoEncontrado.getCosto());
            productoBuscado.setCodigo(productoEncontrado.getCodigo());

            return productoBuscado;

        } else {
            return null;
        }
    }

    /**
     * Permite modificar la cantidad de un producto en el inventario.
     *
     * @param productoActualizado El producto con la cantidad modificada
     * @throws ObjetosNegocioException Si no se puede modificar la cantidad del
     * producto
     */
    @Override
    public void modCantidadProducto(ProductoDTO productoActualizado) throws ObjetosNegocioException {

        Producto producto = new Producto();

        producto.setCodigo(productoActualizado.getCodigo());
        producto.setNombre(producto.getNombre());
        producto.setMarca(producto.getMarca());
        producto.setCantidad(productoActualizado.getCantidad());
        producto.setCosto(productoActualizado.getCosto());

        try {
            productosDAO.modCantidadProducto(producto);
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException(ex.getMessage());
        }
    }

    /**
     * Método el cuál le asigna un proveedor al producto
     *
     * @param productoDTO producto al que se le desea asigar un proveedor
     * @param proveedorDTO provedoor que se desea asignar
     */
    @Override
    public void asignarProveedor(ProductoDTO productoDTO, ProveedorDTO proveedorDTO) throws ObjetosNegocioException {
        if (obtenerProducto(productoDTO) != null) {
            Producto producto = new Producto();
            producto.setCodigo(productoDTO.getCodigo());

            ProveedorBO proveedorBO = new ProveedorBO();

            if (proveedorBO.buscarProveedor(proveedorDTO) != null) {
                Proveedor proveedor = new Proveedor();
                proveedor.setRfc(proveedorDTO.getRfc());

                try {
                    productosDAO.asignarProveedor(producto, proveedor);
                } catch (PersistenciaException ex) {
                    throw new ObjetosNegocioException(ex.getMessage());
                }

            } else {
                throw new ObjetosNegocioException("Error: El proveedor no esta registrado");
            }
        } else {
            throw new ObjetosNegocioException("Error: El producto no esta registrado");

        }

    }

    /**
     * Permite obtener una lista de los productos del inventario filtrados por
     * su nombre.
     *
     * @param nombre El nombre de los productos que se desean obtener
     * @return La lista de los productos consultados
     */
    @Override
    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {

        Producto producto = new Producto();
        producto.setNombre(nombre);

        List<Producto> productosSemejantes = productosDAO.buscarProductosPorNombre(producto);

        List<ProductoDTO> productosDTO = new LinkedList<>();

        for (Producto productoSemejante : productosSemejantes) {
            ProductoDTO productoSemejanteDTO = new ProductoDTO();
            productoSemejanteDTO.setCantidad(productoSemejante.getCantidad());
            productoSemejanteDTO.setCodigo(productoSemejante.getCodigo());
            productoSemejanteDTO.setCosto(productoSemejante.getCosto());
            productoSemejanteDTO.setMarca(productoSemejante.getMarca());
            productoSemejanteDTO.setNombre(productoSemejante.getNombre());

            productosDTO.add(productoSemejanteDTO);
        }
        return productosDTO;
    }

    /**
     * Este método registra un nuevo producto
     *
     * @param productoDTO producto que se desea registrar
     * @return retorna el producto registrado
     * @throws ObjetosNegocioException en caso de no lograr registrar el
     * producto
     */
    @Override
    public ProductoDTO registrarProducto(ProductoDTO productoDTO) throws ObjetosNegocioException {

        if (obtenerProducto(productoDTO) == null) {
            Producto producto = new Producto();
            producto.setCodigo(productoDTO.getCodigo());
            producto.setNombre(productoDTO.getNombre());
            producto.setMarca(productoDTO.getMarca());
            producto.setCosto(productoDTO.getCosto());
            try {
                producto = productosDAO.registrarProducto(producto);
                return productoDTO;
            } catch (PersistenciaException ex) {
                throw new ObjetosNegocioException("Error: No fue posible registrar el producto");
            }
        } else {
            throw new ObjetosNegocioException("Error: No fue posible registrar el producto");
        }

    }

    /**
     * Método que obtiene todos los productos del inventario
     *
     * @return lista de productos
     */
    @Override
    public List<ProductoDTO> obtnerInventario() {
        List<Producto> productos = productosDAO.obtenerInventario();
        List<ProductoDTO> productosDTO = new LinkedList<>();

        for (Producto p : productos) {
            ProductoDTO producto = new ProductoDTO();
            producto.setNombre(p.getNombre());
            producto.setCodigo(p.getCodigo());
            producto.setCosto(p.getCosto());
            producto.setMarca(p.getMarca());
            producto.setCantidad(p.getCantidad());
            productosDTO.add(producto);
        }
        return productosDTO;

    }

    /**
     * Método que modifica los atributos de un producto
     *
     * @param producto produto que se desea modificar
     * @return producto modificado
     */
    @Override
    public void actualizarProducto(ProductoDTO producto) throws ObjetosNegocioException {

        Producto productoActualizar = new Producto();

        productoActualizar.setCodigo(producto.getCodigo());
        productoActualizar.setCosto(producto.getCosto());
        productoActualizar.setMarca(producto.getMarca());
        productoActualizar.setNombre(producto.getNombre());

        try {
            productosDAO.actualizarProducto(productoActualizar);
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException(ex.getMessage());
        }

    }

}

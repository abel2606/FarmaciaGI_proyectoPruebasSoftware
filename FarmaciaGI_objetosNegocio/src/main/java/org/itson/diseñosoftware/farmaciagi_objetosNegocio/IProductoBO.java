package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

/**
 *
 * @author Hector Espinoza
 */
public interface IProductoBO {
    
    /**
     * Este método obtiene un producto de tipo DTO del inventario por medio del
     * código único de un producto
     *
     * @param productoBuscado producto que se desea buscar
     * @return producto que se encontró
     */
    public ProductoDTO obtenerProducto(ProductoDTO productoBuscado);

    /**
     * Permite modificar la cantidad de un producto en el inventario.
     *
     * @param productoActualizado El producto con la cantidad modificada
     * @throws ObjetosNegocioException Si no se puede modificar la cantidad del
     * producto
     */
    public void modCantidadProducto(ProductoDTO productoActualizado) throws ObjetosNegocioException ;

    /**
     * Método el cuál le asigna un proveedor al producto
     * @param producto producto al que se le desea asigar un proveedor
     * @param proveedor provedoor que se desea asignar
     */
    public void asignarProveedor (ProductoDTO productoDTO, ProveedorDTO proveedorDTO) throws ObjetosNegocioException;

    /**
     * Permite obtener una lista de los productos del inventario filtrados por
     * su nombre.
     *
     * @param nombre El nombre de los productos que se desean obtener
     * @return La lista de los productos consultados
     */
    public List<ProductoDTO> buscarProductosPorNombre(String nombre);

    /**
     * Este método registra un nuevo producto
     * @param productoDTO producto que se desea registrar
     * @return retorna el producto registrado
     * @throws ObjetosNegocioException en caso de no lograr registrar el producto
     */
    public ProductoDTO registrarProducto (ProductoDTO productoDTO) throws ObjetosNegocioException;
    
    /**
     * Método que obtiene todos los productos del inventario
     * @return lista de productos
     */
    public List<ProductoDTO> obtnerInventario();
    
    /**
     * Método que modifica los atributos de un producto
     * @param producto produto que se desea modificar
     */
    public void actualizarProducto (ProductoDTO producto) throws ObjetosNegocioException;
}

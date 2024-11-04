package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import java.util.List;
import org.bson.types.ObjectId;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Proveedor;

public interface IProductosDAO {

    /**
     * Método el cual registra un proucto al inventario
     *
     * @param nuevoProducto producto a registrar
     * @return producto registrado
     * @throws PersistenciaException en caso de no lograr registrar el producto
     */
    public Producto registrarProducto(Producto nuevoProducto) throws PersistenciaException;

    /**
     * Método el cual actualiza un producto del inventario
     *
     * @param productoActualizar producto a actualizar
     * @return producto actualizado
     * @throws PersistenciaException en caso de no lograr actualizar el producto
     */
    public Producto actualizarProducto(Producto productoActualizar) throws PersistenciaException;

    /**
     * Método el cual modifica con una suma la cantidad del producto del
     * inventario
     *
     * @param producto producto al que se le desea cambiar su cantidad en stock
     * @throws PersistenciaException en caso de no lograr encontrar el producto
     */
    public void modCantidadProducto(Producto producto) throws PersistenciaException;

    /**
     * Método el cual elimina un producto del inventario
     *
     * @param producto producto que se desea eliminar
     * @return producto eliminado
     * @throws PersistenciaException en caso de no eliminar el producto
     */
    public Producto eliminarProducto(Producto producto) throws PersistenciaException;

    /**
     * Método el cual obtiene un producto del inventario
     *
     * @param producto producto que se desea obtener
     * @return producto que se obtiene
     */
    public Producto obtenerProducto(Producto producto);

    /**
     * Método el cual obtiene los productos semejantes a un nombre en la base de
     * datos
     *
     * @param producto producto que se desea encontrar
     * @return Lista de productos encontrados
     */
    public List<Producto> buscarProductosPorNombre(Producto producto);

    /**
     * Este método asigna un proveedor a un producto 
     * @param proveedor proveedor que se desea asignar
     * @param producto producto al que se le desea asignar el proveedor
     * @throws PersistenciaException en caso de algún error
     */
    public void asignarProveedor(Producto producto, Proveedor proveedor)throws PersistenciaException;
    
    /**
     * Este método actualiza la lista actualde proveedores de un producto por otra lista
     * @param proveedores nueva lista de proveedores
     * @param producto producto que se desea modificar
     * @return Producto actualizado
     * @throws En caso de no actualizar la lista de proveedores
     */
    public Producto actualizarProveedores (List<ObjectId> proveedores, Producto producto) throws PersistenciaException;
    
    /**
     * Este método retorna todos los productos del inventario.
     * @return Lista de productos registrados
     */
    public List<Producto> obtenerInventario();
    
    /**
     * Este método elimina un proveedor de todos los productos que lo contengan
     * @param proveedor proveedor a eiminar
     */
    public void eliminarProveedor(Proveedor proveedor);
}

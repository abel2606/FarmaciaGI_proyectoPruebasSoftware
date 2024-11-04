package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import java.util.List;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;

public interface IProveedoresDAO {
    
    /**
     * Registra un nuevo proveedor.
     * @param proveedorNuevo proveedor nuevo a registrar.
     * @return El proveedor registrado.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    public Proveedor registrarProveedor(Proveedor proveedorNuevo) throws PersistenciaException;
    
    /**
     * Elimina un proveedor existente.
     * @param proveedor proveedor a eliminar de la lista de provedores.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    public void eliminarProveedor(Proveedor proveedor) throws PersistenciaException;
    
    /**
     * Actualiza los datos de un proveedor existente.
     * @param proveedorActualizar proveedor a actualizar sus datos.
     * @return El proveedor actualizado.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    public Proveedor actualizarProveedor(Proveedor proveedorActualizar) throws PersistenciaException;
    
    /**
     * Obtiene un proveedor.
     * @param proveedor proveedor a buscar.
     * @return El proveedor obtenido.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    public Proveedor obtenerProveedor(Proveedor proveedor) throws PersistenciaException;
    
    /**
     * Obtiene una lista de todos los proveedores registrados.
     * @return Una lista de proveedores.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    public List<Proveedor> obtenerProveedores() throws PersistenciaException;
}

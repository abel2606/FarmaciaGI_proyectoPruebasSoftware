package org.itson.disenosoftware.farmaciagi_subsistema_proveedores;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.GestorProveedoresException;

public interface IGestorProveedores {
    
    /**
     * Registra un nuevo proveedor en la base de datos.
     * @param proveedorNuevoDTO Datos del proveedor a registrar.
     * @return Los datos del proveedor registrado o null si hay un error.
     */
    public ProveedorDTO registrarProveedor(ProveedorDTO proveedorNuevoDTO);
    
    /**
     * Elimina un proveedor de la base de datos.
     * @param proveedorEliminar Datos del proveedor a eliminar.
     * @throws org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.GestorProveedoresException
     */
    public void eliminarProveedor(ProveedorDTO proveedorEliminar) throws GestorProveedoresException;
    
    /**
     * Actualiza los datos de un proveedor en la base de datos.
     * @param proveedorActualizar Datos actualizados del proveedor.
     * @throws org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.GestorProveedoresException
     */
    public void actualizarProveedor(ProveedorDTO proveedorActualizar) throws GestorProveedoresException;
    
    /**
     * Busca un proveedor en la base de datos por su RFC.
     * @param proveedorBuscar Datos del proveedor a buscar.
     * @return Los datos del proveedor encontrado o null si no se encuentra.
     */
    public ProveedorDTO buscarProveedor(ProveedorDTO proveedorBuscar);
    
    /**
     * Busca y devuelve todos los proveedores en la base de datos.
     * @return Una lista de todos los proveedores encontrados o null si no hay ninguno.
     */
    public List<ProveedorDTO> buscarProveedores();
}

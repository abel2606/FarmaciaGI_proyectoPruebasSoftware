package org.itson.disenosoftware.farmaciagi_subsistema_proveedores;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.ControlProveedoresException;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.IProveedorBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.ProveedorBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class ControlGestorProveedores {
    private IProveedorBO proveedor;

    public ControlGestorProveedores() {
        this.proveedor = new ProveedorBO();
    }
    
    /**
     * Registra un nuevo proveedor en la base de datos.
     * @param proveedorNuevoDTO Datos del proveedor a registrar.
     * @return Los datos del proveedor registrado o null si hay un error.
     */
    public ProveedorDTO registrarProveedor(ProveedorDTO proveedorNuevoDTO){
        return proveedor.registrarProveedor(proveedorNuevoDTO);
    }
    
    /**
     * Elimina un proveedor de la base de datos.
     * @param proveedorEliminar Datos del proveedor a eliminar.
     * @throws org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.ControlProveedoresException
     */
    public void eliminarProveedor(ProveedorDTO proveedorEliminar) throws ControlProveedoresException{
        try {
            proveedor.eliminarProveedor(proveedorEliminar);
        } catch (ObjetosNegocioException ex) {
            throw new ControlProveedoresException(ex.getMessage());
        }
    }
    
    /**
     * Actualiza los datos de un proveedor en la base de datos.
     * @param proveedorActualizar Datos actualizados del proveedor.
     * @throws org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.ControlProveedoresException
     */
    public void actualizarProveedor(ProveedorDTO proveedorActualizar) throws ControlProveedoresException{
        try {
            proveedor.actualizarProveedor(proveedorActualizar);
        } catch (ObjetosNegocioException ex) {
            throw new ControlProveedoresException(ex.getMessage());
        }
    }
    
    /**
     * Busca un proveedor en la base de datos por su RFC.
     * @param proveedorBuscar Datos del proveedor a buscar.
     * @return Los datos del proveedor encontrado o null si no se encuentra.
     */
    public ProveedorDTO buscarProveedor(ProveedorDTO proveedorBuscar){
        return proveedor.buscarProveedor(proveedorBuscar);
    }
    
    /**
     * Busca y devuelve todos los proveedores en la base de datos.
     * @return Una lista de todos los proveedores encontrados o null si no hay ninguno.
     */
    public List<ProveedorDTO> buscarProveedores(){
        return proveedor.buscarProveedores();
    }
}

package org.itson.disenosoftware.farmaciagi_subsistema_proveedores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.ControlProveedoresException;
import org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones.GestorProveedoresException;

public class GestorProveedores implements IGestorProveedores{
    private ControlGestorProveedores control;

    /**
     * Constructor.
     */
    public GestorProveedores() {
        this.control = new ControlGestorProveedores();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ProveedorDTO registrarProveedor(ProveedorDTO proveedorNuevoDTO) {
        return control.registrarProveedor(proveedorNuevoDTO);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void eliminarProveedor(ProveedorDTO proveedorEliminar) throws GestorProveedoresException{
        try {
            control.eliminarProveedor(proveedorEliminar);
        } catch (ControlProveedoresException ex) {
            throw new GestorProveedoresException("No se pudo eliminar el proveedor.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void actualizarProveedor(ProveedorDTO proveedorActualizar) throws GestorProveedoresException{
        try {
            control.actualizarProveedor(proveedorActualizar);
        } catch (ControlProveedoresException ex) {
            throw new GestorProveedoresException("No se pudo actualizar el proveedor.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ProveedorDTO buscarProveedor(ProveedorDTO proveedorBuscar) {
        return control.buscarProveedor(proveedorBuscar);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ProveedorDTO> buscarProveedores() {
        return control.buscarProveedores();
    }
    
    
}

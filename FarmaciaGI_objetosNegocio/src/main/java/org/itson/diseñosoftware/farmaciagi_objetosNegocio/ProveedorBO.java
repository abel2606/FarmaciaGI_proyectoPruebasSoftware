package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IProveedoresDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ProveedoresDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Direccion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class ProveedorBO implements IProveedorBO {

    private IProveedoresDAO provedoresDAO;
    private static final Logger LOG = Logger.getLogger(ProveedorBO.class.getName());

    /**
     * Constructor de la clase ProveedorBO que inicializa la conexión y el DAO
     * de proveedores.
     */
    public ProveedorBO() {
        IConexion conexion = new Conexion();
        this.provedoresDAO = new ProveedoresDAO(conexion);
    }

    /**
     * Registra un nuevo proveedor en la base de datos.
     *
     * @param proveedorNuevoDTO Datos del proveedor a registrar.
     * @return Los datos del proveedor registrado o null si hay un error.
     */
    @Override
    public ProveedorDTO registrarProveedor(ProveedorDTO proveedorNuevoDTO) {
        try {
            Proveedor proveedorRegistrar = new Proveedor(
                    proveedorNuevoDTO.getNombre(),
                    new Direccion(
                            proveedorNuevoDTO.getDireccion().getCalle(),
                            proveedorNuevoDTO.getDireccion().getColonia(),
                            proveedorNuevoDTO.getDireccion().getNumero(),
                            proveedorNuevoDTO.getDireccion().getCodigo_postal(),
                            proveedorNuevoDTO.getDireccion().getCiudad()
                    ),
                    proveedorNuevoDTO.getTelefonos(),
                    proveedorNuevoDTO.getRfc()
            );
            provedoresDAO.registrarProveedor(proveedorRegistrar);
            if (buscarProveedor(proveedorNuevoDTO) != null) {
                return proveedorNuevoDTO;
            } else {
                LOG.log(Level.SEVERE, "Error: proveedor no existente");
                return null;
            }
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error: error de persisitencia");
            return null;
        }
    }

    /**
     * Elimina un proveedor de la base de datos.
     *
     * @param proveedorEliminar Datos del proveedor a eliminar.
     * @throws
     * org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException
     */
    @Override
    public void eliminarProveedor(ProveedorDTO proveedorEliminar) throws ObjetosNegocioException {
        Proveedor proveedor = new Proveedor();
        proveedor.setRfc(proveedorEliminar.getRfc());
        try {
            proveedor = provedoresDAO.obtenerProveedor(proveedor);

            try {
                IConexion conexion = new Conexion();
                IProductosDAO productosDAO = new ProductosDAO(conexion);
                provedoresDAO.eliminarProveedor(proveedor);
                productosDAO.eliminarProveedor(proveedor);
            } catch (PersistenciaException ex) {
                throw new ObjetosNegocioException(ex.getMessage());
            }

        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException(ex.getMessage());
        }

    }

    /**
     * Actualiza los datos de un proveedor en la base de datos.
     *
     * @param proveedorActualizar Datos actualizados del proveedor.
     * @throws
     * org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException
     */
    @Override
    public void actualizarProveedor(ProveedorDTO proveedorActualizar) throws ObjetosNegocioException {
        Proveedor proveedor = new Proveedor(
                proveedorActualizar.getNombre(),
                new Direccion(
                        proveedorActualizar.getDireccion().getCalle(),
                        proveedorActualizar.getDireccion().getColonia(),
                        proveedorActualizar.getDireccion().getNumero(),
                        proveedorActualizar.getDireccion().getCodigo_postal(),
                        proveedorActualizar.getDireccion().getCiudad()
                ),
                proveedorActualizar.getTelefonos(),
                proveedorActualizar.getRfc()
        );

        try {
            provedoresDAO.actualizarProveedor(proveedor);
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException(ex.getMessage());
        }
    }

    /**
     * Busca un proveedor en la base de datos por su RFC.
     *
     * @param proveedorBuscar Datos del proveedor a buscar.
     * @return Los datos del proveedor encontrado o null si no se encuentra.
     */
    @Override
    public ProveedorDTO buscarProveedor(ProveedorDTO proveedorBuscar) {
        Proveedor proveedorBD = new Proveedor();
        proveedorBD.setRfc(proveedorBuscar.getRfc());
        Proveedor proveedorEncontrado = new Proveedor();

        try {
            proveedorEncontrado = provedoresDAO.obtenerProveedor(proveedorBD);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProveedorBO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (proveedorEncontrado != null) {
            proveedorBuscar.setNombre(proveedorEncontrado.getNombre());
            proveedorBuscar.setTelefonos(proveedorEncontrado.getTelefonos());

            DireccionDTO direccion = new DireccionDTO(
                    proveedorEncontrado.getDireccion().getCalle(),
                    proveedorEncontrado.getDireccion().getColonia(),
                    proveedorEncontrado.getDireccion().getNumero(),
                    proveedorEncontrado.getDireccion().getCodigo_postal(),
                    proveedorEncontrado.getDireccion().getCiudad()
            );

            proveedorBuscar.setDireccion(direccion);
            return proveedorBuscar;
        } else {
            return null;
        }
    }

    /**
     * Busca y devuelve todos los proveedores en la base de datos.
     *
     * @return Una lista de todos los proveedores encontrados o null si no hay
     * ninguno.
     */
    @Override
    public List<ProveedorDTO> buscarProveedores() {
        List<ProveedorDTO> proveedores = new ArrayList<>();

        try {
            List<Proveedor> proveedoresEncontrados = provedoresDAO.obtenerProveedores();
            for (Proveedor proveedorEncontrado : proveedoresEncontrados) {
                ProveedorDTO proveedor = new ProveedorDTO(
                        proveedorEncontrado.getNombre(),
                        new DireccionDTO(
                                proveedorEncontrado.getDireccion().getCalle(),
                                proveedorEncontrado.getDireccion().getColonia(),
                                proveedorEncontrado.getDireccion().getNumero(),
                                proveedorEncontrado.getDireccion().getCodigo_postal(),
                                proveedorEncontrado.getDireccion().getCiudad()
                        ),
                        proveedorEncontrado.getTelefonos(),
                        proveedorEncontrado.getRfc()
                );
                proveedores.add(proveedor);
            }
            if (!proveedores.isEmpty()) {
                return proveedores;
            } else {
                return new ArrayList<>();
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProveedorBO.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
}

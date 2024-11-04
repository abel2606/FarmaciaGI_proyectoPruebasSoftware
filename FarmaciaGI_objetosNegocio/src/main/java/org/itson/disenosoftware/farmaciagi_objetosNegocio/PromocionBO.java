package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import java.util.LinkedList;
import java.util.List;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IPromocionesDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.PromocionesDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Promocion;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class PromocionBO implements IPromocionBO {

    private IPromocionesDAO promocionesDAO;
    private IConexion conexion;

    /**
     * Constructor.
     */
    public PromocionBO() {
        conexion = new Conexion();
        promocionesDAO = new PromocionesDAO(conexion);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PromocionDTO obtenerPromocion(PromocionDTO promocionBuscada) {
        Promocion promocionAuxiliar = new Promocion(promocionBuscada.getCodigo());

        Promocion promocionRegistro = promocionesDAO.obtenerPromocion(promocionAuxiliar);

        if (promocionRegistro == null) {
            return null;
        }
        
        

        ProductoDTO productoAuxiliar = new ProductoDTO(
                promocionRegistro.getProducto().getCodigo(),
                promocionRegistro.getProducto().getNombre(),
                promocionRegistro.getProducto().getCosto(),
                promocionRegistro.getProducto().getMarca(),
                promocionRegistro.getProducto().getCantidad());

        PromocionDTO promocion = new PromocionDTO(promocionRegistro.getCodigo(), promocionRegistro.getDescripcion(),
                productoAuxiliar, promocionRegistro.getCantidad(), promocionRegistro.getPrecioUnitario());

        return promocion;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void registrarPromocion(PromocionDTO promocionNueva) throws ObjetosNegocioException {
        PromocionDTO promocionRegistro = obtenerPromocion(promocionNueva);

        if (promocionRegistro == null) {
            Producto producto = new Producto(
                    promocionNueva.getProducto().getNombre(),
                    promocionNueva.getProducto().getCosto(),
                    promocionNueva.getProducto().getCodigo());
            producto.setMarca(promocionNueva.getProducto().getMarca());

            Promocion promocion = new Promocion(promocionNueva.getCodigo(), promocionNueva.getDescripcion(),
                    producto, promocionNueva.getCantidad(), promocionNueva.getPrecioUnitario());
            
            try {
                promocionesDAO.registrarPromocion(promocion);
            } catch (PersistenciaException ex) {
                throw new ObjetosNegocioException("No se pudo registrar la promoción.");
            }
        } else {
            throw new ObjetosNegocioException("La promoción ya existe.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void actualizarPromocion(PromocionDTO promocionActualizada) throws ObjetosNegocioException {
        PromocionDTO promocionRegistro = obtenerPromocion(promocionActualizada);

        if (promocionRegistro != null) {
            Producto producto = new Producto(
                    promocionActualizada.getProducto().getNombre(),
                    promocionActualizada.getProducto().getMarca(),
                    promocionActualizada.getProducto().getCosto(),
                    promocionActualizada.getProducto().getCodigo(),
                    promocionActualizada.getProducto().getCantidad());

            Promocion promocion = new Promocion(promocionActualizada.getCodigo(), promocionActualizada.getDescripcion(),
                    producto, promocionActualizada.getCantidad(), promocionActualizada.getPrecioUnitario());

            try {
                promocionesDAO.actualizarPromocion(promocion);
            } catch (PersistenciaException ex) {
                throw new ObjetosNegocioException("No se pudo actualizar la promoción.");
            }
        } else {
            throw new ObjetosNegocioException("La promoción no existe.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void eliminarPromocion(PromocionDTO promocionEliminar) throws ObjetosNegocioException {
        PromocionDTO promocionRegistro = obtenerPromocion(promocionEliminar);

        if (promocionRegistro != null) {
            Promocion promocio = new Promocion(promocionEliminar.getCodigo());

            try {
                promocionesDAO.eliminarPromocion(promocio);
            } catch (PersistenciaException ex) {
                throw new ObjetosNegocioException("No se pudo eliminar la promoción.");
            }
        } else {
            throw new ObjetosNegocioException("La promoción no existe.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<PromocionDTO> obtenerPromociones() {
        List<Promocion> promocionesRegistro = promocionesDAO.obtenerPromociones();
        List<PromocionDTO> promociones = new LinkedList<>();

        for (Promocion promocionRegistro : promocionesRegistro) {
            ProductoDTO productoAuxiliar = new ProductoDTO(
                    promocionRegistro.getProducto().getCodigo(),
                    promocionRegistro.getProducto().getNombre(),
                    promocionRegistro.getProducto().getCosto(),
                    promocionRegistro.getProducto().getMarca(),
                    promocionRegistro.getProducto().getCantidad());

            PromocionDTO promocion = new PromocionDTO(promocionRegistro.getCodigo(), promocionRegistro.getDescripcion(),
                    productoAuxiliar, promocionRegistro.getCantidad(), promocionRegistro.getPrecioUnitario());

            promociones.add(promocion);
        }

        return promociones;
    }

}

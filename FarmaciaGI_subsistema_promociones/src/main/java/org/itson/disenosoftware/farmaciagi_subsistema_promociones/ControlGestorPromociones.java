package org.itson.disenosoftware.farmaciagi_subsistema_promociones;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_promociones.excepciones.SubsistemaPromocionesException;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.IPromocionBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.PromocionBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class ControlGestorPromociones {
    
    private IPromocionBO promocionBO;

    /**
     * Constructor.
     */
    public ControlGestorPromociones() {
        promocionBO = new PromocionBO();
    }

    /**
     * Permite obtener una promoción del registro.
     *
     * @param promocionBuscada La promoción que se desea obtener
     * @return La promoción obtenida del registro
     */
    public PromocionDTO obtenerPromocion(PromocionDTO promocionBuscada) {
        return promocionBO.obtenerPromocion(promocionBuscada);
    }

    /**
     * Permite registrar una promoción nueva.
     *
     * @param promocionNueva La promoción a registrar
     * @throws SubsistemaPromocionesException Si no se puede registrar la
     * promoción
     */
    public void registrarPromocion(PromocionDTO promocionNueva) throws SubsistemaPromocionesException {
        try {
            promocionBO.registrarPromocion(promocionNueva);
        } catch (ObjetosNegocioException ex) {
            throw new SubsistemaPromocionesException("No se pudo registrar la promoción.");
        }
    }

    /**
     * Permite actualizar una promoción en el registro.
     *
     * @param promocionActualizada La promoción a actualizar
     * @throws SubsistemaPromocionesException Si no se puede actualizar la
     * promoción
     */
    public void actualizarPromocion(PromocionDTO promocionActualizada) throws SubsistemaPromocionesException {
        try {
            promocionBO.actualizarPromocion(promocionActualizada);
        } catch (ObjetosNegocioException ex) {
            throw new SubsistemaPromocionesException("No se pudo actualizar la promoción.");
        }
    }

    /**
     * Permite eliminar una promoción del registro.
     *
     * @param promocionEliminar La promoción a eliminar
     * @throws SubsistemaPromocionesException Si no se puede eliminar la
     * promoción del registro
     */
    public void eliminarPromocion(PromocionDTO promocionEliminar) throws SubsistemaPromocionesException {
        try {
            promocionBO.eliminarPromocion(promocionEliminar);
        } catch (ObjetosNegocioException ex) {
            throw new SubsistemaPromocionesException("No se pudo eliminar la promoción.");
        }
    }

    /**
     * Permite obtener una lista con todas las promociones del registro.
     *
     * @return Una lista con todas las promociones del registro
     */
    public List<PromocionDTO> obtenerPromociones() {
        return promocionBO.obtenerPromociones();
    }
}

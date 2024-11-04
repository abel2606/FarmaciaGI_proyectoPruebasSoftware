package org.itson.disenosoftware.farmaciagi_subsistema_promociones;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_promociones.excepciones.SubsistemaPromocionesException;

public class GestorPromociones implements IGestorPromociones {

    private ControlGestorPromociones control;

    /**
     * Constructor.
     */
    public GestorPromociones() {
        control = new ControlGestorPromociones();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PromocionDTO obtenerPromocion(PromocionDTO promocionBuscada) {
        return control.obtenerPromocion(promocionBuscada);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarPromocion(PromocionDTO promocionNueva) throws SubsistemaPromocionesException {
        control.registrarPromocion(promocionNueva);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarPromocion(PromocionDTO promocionActualizada) throws SubsistemaPromocionesException {
        control.actualizarPromocion(promocionActualizada);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarPromocion(PromocionDTO promocionEliminar) throws SubsistemaPromocionesException {
        control.eliminarPromocion(promocionEliminar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PromocionDTO> obtenerPromociones() {
        return control.obtenerPromociones();
    }

}

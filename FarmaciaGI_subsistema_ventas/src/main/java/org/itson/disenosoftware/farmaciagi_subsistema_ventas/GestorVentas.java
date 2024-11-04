package org.itson.disenosoftware.farmaciagi_subsistema_ventas;

import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_ventas.excepciones.ControlVentasException;
import org.itson.disenosoftware.farmaciagi_subsistema_ventas.excepciones.GestorVentasException;

public class GestorVentas implements IGestorVentas {

    private ControlGestorVentas control;

    /**
     * Constructor.
     */
    public GestorVentas() {
        control = new ControlGestorVentas();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void registrarVenta(VentaDTO ventaNueva) throws GestorVentasException {
        try {
            control.registrarVenta(ventaNueva);
        } catch (ControlVentasException ex) {
            throw new GestorVentasException("No se pudo registrar la venta.");
        }
    }

}

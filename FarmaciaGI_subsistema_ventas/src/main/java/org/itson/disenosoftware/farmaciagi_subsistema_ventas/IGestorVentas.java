package org.itson.disenosoftware.farmaciagi_subsistema_ventas;

import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_ventas.excepciones.GestorVentasException;

public interface IGestorVentas {

    /**
     * Permite registrar una venta en el registro.
     *
     * @param venta La venta que se va a registrar
     * @throws GestorVentasException Si no se puede registrar la venta
     */
    public void registrarVenta(VentaDTO venta) throws GestorVentasException;

}

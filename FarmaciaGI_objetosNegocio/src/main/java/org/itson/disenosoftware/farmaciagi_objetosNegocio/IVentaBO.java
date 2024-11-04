package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public interface IVentaBO {

    /**
     * Permite registrar una venta en el registro.
     *
     * @param ventaNueva La venta a registrar
     * @throws ObjetosNegocioException Si no se puede registrar la venta
     */
    public void registrarVenta(VentaDTO ventaNueva) throws ObjetosNegocioException;
    
}

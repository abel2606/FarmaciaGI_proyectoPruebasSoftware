package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Venta;

public interface IVentasDAO {

    /**
     * Método el cual nos permitirá registrar una venta a la bd
     *
     * @param venta venta a registrar
     * @return regresa la venta en caso de éxito
     * @throws PersistenciaException en caso de no poder registrarla
     */
    public Venta registrarVenta(Venta venta) throws PersistenciaException;

    /**
     * Este método se encarga de encontrar ua venta en la bd
     *
     * @param codigo código de la venta que se desea encontrar
     * @return retorna la venta que se encontro
     */
    public Venta encontrarVenta(String codigo);

}

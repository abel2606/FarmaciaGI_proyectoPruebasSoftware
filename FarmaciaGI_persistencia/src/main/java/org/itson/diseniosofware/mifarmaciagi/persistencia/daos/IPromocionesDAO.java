package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import java.util.List;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Promocion;

public interface IPromocionesDAO {

    /**
     * Permite obtener una promoción del registro.
     *
     * @param promocionBuscada La promoción que se desea obtener
     * @return La promoción obtenida del registro
     */
    public Promocion obtenerPromocion(Promocion promocionBuscada);

    /**
     * Permite registrar una promoción nueva.
     *
     * @param promocionNueva La promoción a registrar
     * @throws PersistenciaException Si no se puede registrar la promoción
     */
    public void registrarPromocion(Promocion promocionNueva) throws PersistenciaException;

    /**
     * Permite actualizar una promoción en el registro.
     *
     * @param promocionActualizada La promoción a actualizar
     * @throws PersistenciaException Si no se puede actualizar la promoción
     */
    public void actualizarPromocion(Promocion promocionActualizada) throws PersistenciaException;

    /**
     * Permite eliminar una promoción del registro.
     *
     * @param promocionEliminar La promoción a eliminar
     * @throws PersistenciaException Si no se puede eliminar la promoción del
     * registro
     */
    public void eliminarPromocion(Promocion promocionEliminar) throws PersistenciaException;

    /**
     * Permite obtener una lista con todas las promociones del registro.
     *
     * @return Una lista con todas las promociones del registro
     */
    public List<Promocion> obtenerPromociones();

}

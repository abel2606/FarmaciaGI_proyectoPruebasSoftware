package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public interface IPromocionBO {

    /**
     * Permite obtener una promoción del registro.
     *
     * @param promocionBuscada La promoción que se desea obtener
     * @return La promoción obtenida del registro
     */
    public PromocionDTO obtenerPromocion(PromocionDTO promocionBuscada);

    /**
     * Permite registrar una promoción nueva.
     *
     * @param promocionNueva La promoción a registrar
     * @throws ObjetosNegocioException Si no se puede registrar la promoción
     */
    public void registrarPromocion(PromocionDTO promocionNueva) throws ObjetosNegocioException;

    /**
     * Permite actualizar una promoción en el registro.
     *
     * @param promocionActualizada La promoción a actualizar
     * @throws ObjetosNegocioException Si no se puede actualizar la promoción
     */
    public void actualizarPromocion(PromocionDTO promocionActualizada) throws ObjetosNegocioException;

    /**
     * Permite eliminar una promoción del registro.
     *
     * @param promocionEliminar La promoción a eliminar
     * @throws ObjetosNegocioException Si no se puede eliminar la promoción del
     * registro
     */
    public void eliminarPromocion(PromocionDTO promocionEliminar) throws ObjetosNegocioException;

    /**
     * Permite obtener una lista con todas las promociones del registro.
     *
     * @return Una lista con todas las promociones del registro
     */
    public List<PromocionDTO> obtenerPromociones();

}

/*
 * IGestorPromocionesTest.java
 */
package org.itson.disenosoftware.farmaciagi_subsistema_promociones;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_promociones.excepciones.SubsistemaPromocionesException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Equipo1
 */
public class IGestorPromocionesTest {

    private IGestorPromociones gestorPromociones;

    public IGestorPromocionesTest() {
        gestorPromociones = new GestorPromociones();
    }

    @Test
    public void ObtenerPromocion_PromocionExistente_PromocionObtenida() {
        //arrange
        PromocionDTO promocionBuscada = new PromocionDTO("PRO-001", "Paracetamol 2x1", new ProductoDTO("PAR-001", "Paracetamol", 20.0f), 2, 10.0f);
        PromocionDTO resultado;
        
        //act
        resultado = gestorPromociones.obtenerPromocion(new PromocionDTO("PRO-001"));
        
        //assert
        assertEquals(promocionBuscada, resultado);
    }

    @Test
    public void RegistrarPromocion_PromocionInexistente_PromocionRegistrada() throws Exception {
        //arrange
        PromocionDTO promocionNueva = new PromocionDTO("PRO-002", "Ibuprofeno -$10.00", new ProductoDTO("IBU-001", "Ibuprofeno", 40.0f), 1, 30.0f);
        
        //act
        gestorPromociones.registrarPromocion(promocionNueva);
    }

    @Test
    public void ActualizarPromocion_PromocionExistente_PromocionActualizada() throws Exception {
        //arrange
        PromocionDTO promocionActualizada = new PromocionDTO("PRO-003", "Ibuprofeno -$5.00", new ProductoDTO("IBU-001", "Ibuprofeno", 40.0f), 1, 35.0f);
        
        //act
        gestorPromociones.actualizarPromocion(promocionActualizada);
    }

    @Test
    public void EliminarPromocion_PromocionExistente_PromocionEliminada() throws Exception {
        //arrange
        PromocionDTO promocionEliminar = new PromocionDTO("PRO-004");
        
        //act
        gestorPromociones.eliminarPromocion(promocionEliminar);
    }

}

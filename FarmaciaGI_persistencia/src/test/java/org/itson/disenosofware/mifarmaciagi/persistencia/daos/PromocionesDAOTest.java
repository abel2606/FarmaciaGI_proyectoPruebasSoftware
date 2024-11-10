/*
 * PromocionesDAOTest.java
 */
package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import java.util.LinkedList;
import java.util.List;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Promocion;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Equipo1
 */
@ExtendWith(MockitoExtension.class)
public class PromocionesDAOTest {

    @Mock
    private IPromocionesDAO promocionesDAO;
    
    @Test
    public void ObtenerPromocion_PromocionExistente_ReturnSuccess() {
        //arrange
        Promocion promocionBuscada = new Promocion("PRO-001", "Paracetamol 2x1", new Producto("Paracetamol", 20.0f, "PAR-001"), 2, 10.0f);
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-001"))).thenReturn(promocionBuscada);
        Promocion resultado;

        //act
        resultado = promocionesDAO.obtenerPromocion(new Promocion("PRO-001"));

        //assert
        assertEquals(promocionBuscada, resultado);
    }

    @Test
    public void ObtenerPromocion_PromocionInexistente_ReturnNull() {
        //arrange
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-002"))).thenReturn(null);
        Promocion resultado;

        //act
        resultado = promocionesDAO.obtenerPromocion(new Promocion("PRO-002"));

        //assert
        assertNull(resultado);
    }

    @Test
    public void RegistrarPromocion_PromocionNueva_PromocionRegistrada() throws Exception {
        //arrange
        Promocion promocionNueva = new Promocion("PRO-005", "Omeprazol 2X1", new Producto("Omeprazol", 35.0f, "OME-011"), 2, 17.5f);
        doNothing().when(promocionesDAO).registrarPromocion(promocionNueva);

        //act
        promocionesDAO.registrarPromocion(promocionNueva);

        //assert
        verify(promocionesDAO, Mockito.times(1)).registrarPromocion(promocionNueva);
    }

    @Test
    public void RegistrarPromocion_PromocionExistente_ThrowException() throws Exception {
        //arrange
        Promocion promocionNueva = new Promocion("PRO-001", "Paracetamol 2X1", new Producto("Paracetamol", 20.0f, "PAR-001"), 2, 10.0f);
        doThrow(new PersistenciaException("Error")).when(promocionesDAO).registrarPromocion(promocionNueva);
        PersistenciaException e = null;

        //act
        try {
            promocionesDAO.registrarPromocion(promocionNueva);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        //assert
        assertNotNull(e);
    }

    @Test
    public void ActualizarPromocion_PromocionExistente_PromocionActualizada() throws Exception {
        //arrange
        Promocion promocionActualida = new Promocion("PRO-005", "Omeprazol 3X2", new Producto("Omeprazol", 35.0f, "OME-011"), 3, 23.3333f);
        doNothing().when(promocionesDAO).actualizarPromocion(promocionActualida);

        //act
        promocionesDAO.actualizarPromocion(promocionActualida);

        //assert
        verify(promocionesDAO, Mockito.times(1)).actualizarPromocion(promocionActualida);
    }

    @Test
    public void ActualizarPromocion_PromocionInexistente_ThrowException() throws Exception {
        //arrange
        Promocion promocionActualizada = new Promocion("PRO-101", "Aspirina 2X1", new Producto("Aspirina", 30.0f, "ASP-001"), 2, 15.0f);
        doThrow(new PersistenciaException("Error")).when(promocionesDAO).actualizarPromocion(promocionActualizada);
        PersistenciaException e = null;

        //act
        try {
            promocionesDAO.actualizarPromocion(promocionActualizada);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        //assert
        assertNotNull(e);
    }

    @Test
    public void EliminarPromocion_PromocionExistente_PromocionEliminada() throws Exception {
        //arrange
        Promocion promocionEliminar = new Promocion("PRO-001");
        doNothing().when(promocionesDAO).eliminarPromocion(promocionEliminar);

        //act
        promocionesDAO.eliminarPromocion(promocionEliminar);

        //assert
        verify(promocionesDAO, Mockito.times(1)).eliminarPromocion(promocionEliminar);
    }
    
    @Test
    public void EliminarPromocion_PromocionInexistente_ThrowException() throws Exception {
        //arrange
        Promocion promocionEliminar = new Promocion("PRO-023");
        doThrow(PersistenciaException.class).when(promocionesDAO).eliminarPromocion(promocionEliminar);
        PersistenciaException e = null;

        //act
        try {
            promocionesDAO.eliminarPromocion(promocionEliminar);
        } catch (PersistenciaException ex) {
            e = ex;
        }
        
        //assert
        assertNotNull(e);
    }

    /**
     * Test of obtenerPromociones method, of class PromocionesDAO.
     */
    @Test
    public void ObtenerPromociones_ListaPromociones_ListaLlena() {
        //arrange
        List<Promocion> promociones = List.of(new Promocion("PRO-002"), new Promocion("PRO-002"), new Promocion("PRO-003"));
        when(promocionesDAO.obtenerPromociones()).thenReturn(promociones);
        List<Promocion> resultado;

        //act
        resultado = promocionesDAO.obtenerPromociones();

        //assert
        assertEquals(promociones, resultado);
        assertEquals(promociones.size(), resultado.size());
    }
    
    @Test
    public void ObtenerPromociones_ListaPromociones_ListaVacía() {
        //arrange
        List<Promocion> promociones = new LinkedList<>();
        when(promocionesDAO.obtenerPromociones()).thenReturn(promociones);
        List<Promocion> resultado;

        //act
        resultado = promocionesDAO.obtenerPromociones();

        //assert
        assertEquals(promociones, resultado);
        assertEquals(promociones.size(), resultado.size());
    }

}

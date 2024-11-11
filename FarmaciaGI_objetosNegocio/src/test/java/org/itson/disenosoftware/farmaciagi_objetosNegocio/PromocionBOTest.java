/*
 * PromocionBOTest.java
 */
package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import java.util.LinkedList;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.IPromocionesDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Promocion;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Equipo1
 */
@ExtendWith(MockitoExtension.class)
public class PromocionBOTest {

    @Mock
    private IPromocionesDAO promocionesDAO;

    @InjectMocks
    private PromocionBO promocionesBO;

    @Test
    public void ObtenerPromocion_PromocionExistente_ObtienePromocion() {
        // arrange
        Promocion promocionBuscada = new Promocion("PRO-001", "Paracetamol 2x1", new Producto("Paracetamol", 20.0f, "PAR-001"), 2, 10.0f);
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-001"))).thenReturn(promocionBuscada);
        PromocionDTO resultado;

        // act
        resultado = promocionesBO.obtenerPromocion(new PromocionDTO("PRO-001"));

        // assert
        assertEquals(promocionBuscada.getCodigo(), resultado.getCodigo());
        verify(promocionesDAO, times(1)).obtenerPromocion(new Promocion("PRO-001"));
    }

    @Test
    public void ObtenerPromocion_PromocionInexistente_ObtienenNulo() {
        // arrange
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-200"))).thenReturn(null);
        PromocionDTO resultado;

        // act
        resultado = promocionesBO.obtenerPromocion(new PromocionDTO("PRO-200"));

        // assert
        assertNull(resultado);
        verify(promocionesDAO, times(1)).obtenerPromocion(new Promocion("PRO-200"));
    }

    @Test
    public void RegistrarPromocion_PromocionNueva_PromocionRegistrada() throws Exception {
        //arrange
        Promocion promocion = new Promocion("PRO-200", "Simi paletas -$50.00", new Producto("Simi paletas", 90.0f, "SIM-011"), 1, 40.0f);
        PromocionDTO promocionNueva = new PromocionDTO("PRO-200", "Simi paletas -$50.00", new ProductoDTO("SIM-011", "Simi paletas", 90.0f), 1, 40.0f);
        doNothing().when(promocionesDAO).registrarPromocion(promocion);

        //act
        promocionesBO.registrarPromocion(promocionNueva);

        //assert
        verify(promocionesDAO, times(1)).registrarPromocion(promocion);
    }

    @Test
    public void RegistrarPromocion_PromocionExistente_LanzaExcepcion() throws Exception {
        //arrange
        Promocion promocion = new Promocion("PRO-200", "Simi paletas -$50.00", new Producto("Simi paletas", 90.0f, "SIM-011"), 1, 40.0f);
        PromocionDTO promocionNueva = new PromocionDTO("PRO-200", "Simi paletas -$50.00", new ProductoDTO("SIM-011", "Simi paletas", 90.0f), 1, 40.0f);
        doThrow(new PersistenciaException("Promocion existente")).when(promocionesDAO).registrarPromocion(promocion);

        //act & assert
        assertThrows(ObjetosNegocioException.class, () -> promocionesBO.registrarPromocion(promocionNueva));
        verify(promocionesDAO, times(1)).registrarPromocion(promocion);
    }

    @Test
    public void ActualizarPromocion_PromocionExistente_PromocionActualizada() throws Exception {
        //arrange
        Promocion promocion = new Promocion("PRO-001", "Paracetamol -$5.00", new Producto("Paracetamol", 20.0f, "PAR-001"), 1, 15.0f);
        PromocionDTO promocionActualizada = new PromocionDTO("PRO-001", "Paracetamol -$5.00", new ProductoDTO("PAR-001", "Paracetamol", 20.0f), 1, 15.0f);
        when(promocionesDAO.obtenerPromocion(promocion)).thenReturn(promocion);
        doNothing().when(promocionesDAO).actualizarPromocion(promocion);

        //act
        promocionesBO.actualizarPromocion(promocionActualizada);

        //assert
        verify(promocionesDAO, times(1)).actualizarPromocion(promocion);
    }

    @Test
    public void ActualizarPromocion_PromocionInxistente_LanzaExcepcion() throws Exception {
        //arrange
        PromocionDTO promocionActualizada = new PromocionDTO("PRO-001", "Paracetamol -$5.00", new ProductoDTO("PAR-001", "Paracetamol", 20.0f), 1, 15.0f);
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-001"))).thenReturn(null);

        //act & assert
        assertThrows(ObjetosNegocioException.class, () -> promocionesBO.actualizarPromocion(promocionActualizada));
        verify(promocionesDAO, times(0)).actualizarPromocion(new Promocion("PR0-001"));
    }

    @Test
    public void EliminarPromocion_PromocionExistente_PromocionEliminada() throws Exception {
        //arrange
        Promocion promocion = new Promocion("PRO-001", "Paracetamol -$5.00", new Producto("Paracetamol", 20.0f, "PAR-001"), 1, 15.0f);
        PromocionDTO promocionEliminar = new PromocionDTO("PRO-001");
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-001"))).thenReturn(promocion);
        doNothing().when(promocionesDAO).eliminarPromocion(new Promocion("PRO-001"));

        //act
        promocionesBO.eliminarPromocion(promocionEliminar);

        //assert
        verify(promocionesDAO, times(1)).eliminarPromocion(new Promocion("PRO-001"));
    }

    @Test
    public void EliminarPromocion_PromocionInxistente_LanzaExcepcion() throws Exception {
        //arrange
        PromocionDTO promocionEliminar = new PromocionDTO("PRO-023");
        when(promocionesDAO.obtenerPromocion(new Promocion("PRO-023"))).thenReturn(null);

        //act & assert
        assertThrows(ObjetosNegocioException.class, () -> promocionesBO.eliminarPromocion(promocionEliminar));
        verify(promocionesDAO, times(0)).eliminarPromocion(new Promocion("PRO-001"));
    }

    @Test
    public void ObtenerPromociones_PromocionesExistentes_ListaLlena() {
        //arrange
        List<Promocion> promociones = List.of(
                new Promocion("PRO-001", "Paracetamol 2x1", new Producto("Paracetamol", 20.0f, "PAR-001"), 2, 10.0f),
                new Promocion("PRO-002", "Ibuprofeno -$5.0", new Producto("Ibuprofeno", 35.0f, "IBU-001"), 1, 30.0f),
                new Promocion("PRO-003", "Aspirina 3x2", new Producto("Aspirina", 30.0f, "ASP-001"), 3, 20.0f));
        when(promocionesDAO.obtenerPromociones()).thenReturn(promociones);
        List<PromocionDTO> resultado;

        //act
        resultado = promocionesBO.obtenerPromociones();

        //assert
        assertEquals(promociones.size(), resultado.size());
        assertEquals(promociones.get(0).getCodigo(), resultado.get(0).getCodigo());
        assertEquals(promociones.get(1).getCodigo(), resultado.get(1).getCodigo());
        assertEquals(promociones.get(2).getCodigo(), resultado.get(2).getCodigo());
        verify(promocionesDAO, times(1)).obtenerPromociones();
    }

    @Test
    public void ObtenerPromociones_PromocionesInexistentes_ListaVacia() {
        //arrange
        List<Promocion> promociones = new LinkedList<>();
        when(promocionesDAO.obtenerPromociones()).thenReturn(promociones);
        List<PromocionDTO> resultado;

        //act
        resultado = promocionesBO.obtenerPromociones();

        //assert
        assertEquals(promociones.size(), resultado.size());
        assertNotNull(resultado);
        verify(promocionesDAO, times(1)).obtenerPromociones();
    }

}

/*
 * ControlGestorPromocionesTest.java
 */
package org.itson.disenosoftware.farmaciagi_subsistema_promociones;

import java.util.ArrayList;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.IPromocionBO;
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
public class ControlGestorPromocionesTest {

    @Mock
    private IPromocionBO promocionesBO;
    
    @InjectMocks
    private ControlGestorPromociones controlPromociones;
    
    private ControlGestorPromociones gestorPromociones;

    public ControlGestorPromocionesTest() {
        gestorPromociones = new ControlGestorPromociones();
    }

    @Test
    public void ObtenerPromocion_PromocionExistente_PromocionObtenida() {
        //arrange
        PromocionDTO promocionEsperada = new PromocionDTO("PRO-001", "Paracetamol 2x1", new ProductoDTO("PAR-001", "Paracetamol", 20.0f), 2, 10.0f);
        when(promocionesBO.obtenerPromocion(new PromocionDTO("PRO-001"))).thenReturn(promocionEsperada);
        PromocionDTO resultado;
        
        //act
        resultado = controlPromociones.obtenerPromocion(new PromocionDTO("PRO-001"));
        
        //assert
        assertEquals(promocionEsperada, resultado);
        assertEquals(promocionEsperada.getCodigo(), resultado.getCodigo());
        verify(promocionesBO, times(1)).obtenerPromocion(new PromocionDTO("PRO-001"));
    }
    
    @Test
    public void ObtenerPromocion_PromocionInexistente_ObtieneNulo() {
        //arrange
        when(promocionesBO.obtenerPromocion(new PromocionDTO("PRO-121"))).thenReturn(null);
        PromocionDTO resultado;
        
        //act
        resultado = controlPromociones.obtenerPromocion(new PromocionDTO("PRO-121"));
        
        //assert
        assertNull(resultado);
        verify(promocionesBO, times(1)).obtenerPromocion(new PromocionDTO("PRO-121"));
    }
    
    @Test
    public void RegistrarPromocion_Registra100Promociones_PromocionesRegistradasMenos10Segundos() throws Exception {
        //arrange
        List<PromocionDTO> promociones = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            promociones.add(new PromocionDTO("PRO-" + (i + 1), "Descripción", new ProductoDTO("PRO-" + (i + 1), "Nombre", 30.0f), 1, 20.0f));
        }

        //act
        for (PromocionDTO promocion : promociones) {
            gestorPromociones.registrarPromocion(promocion);
        }
    }
    
    @Test
    public void RegistrarPromocion_PromocionInexistente_PromocionRegistrada() throws Exception {
        //arrange
        PromocionDTO promocionNueva = new PromocionDTO("PRO-101", "Clonazepam -$20.0", new ProductoDTO("CLO-001", "Clonazepam", 100.0f), 1, 80.0f);
        doNothing().when(promocionesBO).registrarPromocion(promocionNueva);
        
        //act
        controlPromociones.registrarPromocion(promocionNueva);
        
        //assert
        verify(promocionesBO, times(1)).registrarPromocion(promocionNueva);
    }
    
    @Test
    public void ActualizarPromocion_Actualizar100Promociones_PromocionesActualizadasMenos5Segundos() throws Exception {
        //arrange
        List<PromocionDTO> promociones = gestorPromociones.obtenerPromociones();
        
        //act 
        for (PromocionDTO promocion : promociones) {
            gestorPromociones.actualizarPromocion(new PromocionDTO(promocion.getCodigo(), "Promocion Actualizada", promocion.getProducto(), promocion.getCantidad(), promocion.getPrecioUnitario()));
        }
    }
    
    @Test
    public void ActualizarPromocion_PromocionExistente_PromocionActualizada() throws Exception {
        //arrange
        PromocionDTO promocionActualizada = new PromocionDTO("PRO-101", "Clonazepam -$20.0", new ProductoDTO("CLO-001", "Clonazepam", 100.0f), 1, 80.0f);
        doNothing().when(promocionesBO).actualizarPromocion(promocionActualizada);
        
        //act 
        controlPromociones.actualizarPromocion(promocionActualizada);
        
        //assert
        verify(promocionesBO, times(1)).actualizarPromocion(promocionActualizada);
    }

    @Test
    public void EliminarPromocion_Eliminar100Promociones_PromocionesEliminadasMenos5Segundos() throws Exception {
        //arrange
        List<PromocionDTO> promociones = gestorPromociones.obtenerPromociones();
        
        //act 
        for (PromocionDTO promocion : promociones) {
            gestorPromociones.eliminarPromocion(promocion);
        }
    }
    
    @Test
    public void EliminarPromocion_PromocionExistente_PromocionEliminada() throws Exception {
        //arrange
        PromocionDTO promocionEliminar = new PromocionDTO("PRO-101", "Clonazepam -$15.0", new ProductoDTO("CLO-001", "Clonazepam", 100.0f), 1, 80.0f);
        doNothing().when(promocionesBO).eliminarPromocion(promocionEliminar);
        
        //act 
        controlPromociones.eliminarPromocion(promocionEliminar);
        
        //assert
        verify(promocionesBO, times(1)).eliminarPromocion(promocionEliminar);
    }

    @Test
    public void ObtenerPromociones_Obtener100Promociones_PromocionesObtenidasMenos5Segundos() {
        //arrange
        List<PromocionDTO> promocionesEsperadas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            promocionesEsperadas.add(new PromocionDTO("PRO-" + (i + 1), "Descripción", new ProductoDTO("PRO-" + (i + 1), "Nombre", 30.0f), 1, 20.0f));
        }
        List<PromocionDTO> resultado;
        
        //act 
        resultado = gestorPromociones.obtenerPromociones();
    }
    
    @Test
    public void ObtenerPromociones_PromocionesExistentes_ListaLlena() {
        //arrange
        List<PromocionDTO> promocionesEsperadas = List.of(new PromocionDTO("PRO-001"), new PromocionDTO("PRO-002"), new PromocionDTO("PRO-003"));
        when(promocionesBO.obtenerPromociones()).thenReturn(promocionesEsperadas);
        List<PromocionDTO> resultado;
        
        //act 
        resultado = controlPromociones.obtenerPromociones();
        
        //asser
        verify(promocionesBO, times(1)).obtenerPromociones();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.IVentasDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Promocion;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author hisam
 */
public class VentaBOTest {

    public VentaBOTest() {
    }

    @InjectMocks
    private VentaBO ventaBO;

    @Mock
    private IVentasDAO ventasDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registrarVenta_VentaValida_RegistroExitoso() throws ObjetosNegocioException, PersistenciaException {
        // Arrange
        List<ProductoDTO> productos = Arrays.asList(new ProductoDTO("123", "Producto1", 100.0F, "Marca1", 2));
        List<PromocionDTO> promociones = Arrays.asList(new PromocionDTO("123", "descuento", null, 3, 30.5f));
        Calendar fechaVenta = Calendar.getInstance();
        VentaDTO ventaDTO = new VentaDTO("321", productos, promociones, 200.5F, fechaVenta);

        when(ventasDAO.encontrarVenta(anyString())).thenReturn(null);

        // Act
        ventaBO.registrarVenta(ventaDTO);

        // Assert
        verify(ventasDAO, times(1)).registrarVenta(any(Venta.class));
    }

    @Test
    public void registrarVenta_VentaDuplicada_GeneraNuevoCodigo() throws ObjetosNegocioException, PersistenciaException {
        // Arrange
        List<ProductoDTO> productos = Arrays.asList(new ProductoDTO("123", "Producto1", 100.0F, "Marca1", 2));
        List<PromocionDTO> promociones = Arrays.asList(new PromocionDTO("123", "descuento", null, 3, 30.5f));
        Calendar fechaVenta = Calendar.getInstance();
        VentaDTO ventaDTO = new VentaDTO("321", productos, promociones, 200.5F, fechaVenta);

        when(ventasDAO.encontrarVenta(anyString()))
                .thenReturn(new Venta())
                .thenReturn(null);

        // Act
        ventaBO.registrarVenta(ventaDTO);

        // Assert
        verify(ventasDAO, times(1)).registrarVenta(any(Venta.class));
    }

    @Test
    public void registrarVenta_ErrorPersistencia_LanzaExcepcion() throws PersistenciaException {
        // Arrange
        List<ProductoDTO> productos = Arrays.asList(new ProductoDTO("123", "Producto1", 100.0F, "Marca1", 2));
        Calendar fechaVenta = Calendar.getInstance();
        List<PromocionDTO> promociones = Arrays.asList(new PromocionDTO("123", "descuento", null, 3, 30.5f));
        VentaDTO ventaDTO = new VentaDTO("321", productos, promociones, 200.5F, fechaVenta);

        doThrow(new PersistenciaException("Error de persistencia")).when(ventasDAO).registrarVenta(any(Venta.class));

        // Act & Assert
        assertThrows(ObjetosNegocioException.class, () -> ventaBO.registrarVenta(ventaDTO));

    }

    @Test
    public void productosDTOAProductosPOJO_ConversionExitosa_ListaConvertida() {
        // Arrange
        ProductoDTO productoDTO = new ProductoDTO("12345", "Producto1", 50.0F, "Marca1", 10);
        List<ProductoDTO> productosDTO = Arrays.asList(productoDTO);

        // Act
        List<Producto> productos = ventaBO.productosDTOAProductosPOJO(productosDTO);

        // Assert
        assertEquals(1, productos.size());
        assertEquals("12345", productos.get(0).getCodigo());
        assertEquals("Producto1", productos.get(0).getNombre());
        assertEquals(10, productos.get(0).getCantidad());
    }

    @Test
    public void promocionesDTOAPromocionesPOJO_ConversionExitosa_ListaConvertida() throws Exception {
        // Arrange
        ProductoDTO productoDTO = new ProductoDTO("P001", "Producto A", 50.0f, "Marca X");
        PromocionDTO promocionDTO = new PromocionDTO("54321", "Descuento especial", productoDTO, 2, 10.0f);
        List<PromocionDTO> promocionesDTO = Arrays.asList(promocionDTO);

        // Obtener el método privado usando reflexión
        Method method = VentaBO.class.getDeclaredMethod("promocionesDTOAPromocionesPOJO", List.class);
        method.setAccessible(true);

        // Act
        List<Promocion> promociones = (List<Promocion>) method.invoke(ventaBO, promocionesDTO);

        // Assert
        assertEquals(1, promociones.size());
        assertEquals("54321", promociones.get(0).getCodigo());
        assertEquals("Descuento especial", promociones.get(0).getDescripcion());
        assertEquals(2, promociones.get(0).getCantidad());
        assertEquals(10.0f, promociones.get(0).getPrecioUnitario(), 0.0f);

    }

    @Test
    public void registrarVenta_CaminoCondicional_CoberturaCompleta() throws ObjetosNegocioException, PersistenciaException {
        // Arrange
        List<ProductoDTO> productos = Arrays.asList(new ProductoDTO("123", "Producto1", 100.0F, "Marca1", 2));
        List<PromocionDTO> promociones = Arrays.asList(new PromocionDTO("123", "descuento", null, 3, 30.5f));
        Calendar fechaVenta = Calendar.getInstance();
        VentaDTO ventaDTO = new VentaDTO("321", productos, promociones, 200.5F, fechaVenta);

        // Caso 1: No existe una venta previa con el mismo código.
        when(ventasDAO.encontrarVenta(anyString())).thenReturn(null);

        // Act & Assert
        ventaBO.registrarVenta(ventaDTO); // Aquí se debe verificar que el flujo principal se siga sin desvíos.

        verify(ventasDAO, times(1)).registrarVenta(any(Venta.class)); // Se asegura que registrarVenta sea invocado correctamente.

        // Caso 2: Ya existe una venta con el mismo código y se genera uno nuevo.
        when(ventasDAO.encontrarVenta(anyString())).thenReturn(new Venta()).thenReturn(null); // Primera llamada devuelve una venta existente, luego null.

        // Act & Assert
        ventaBO.registrarVenta(ventaDTO); // Ahora se prueba que se registre correctamente con un nuevo código generado.

        verify(ventasDAO, times(2)).registrarVenta(any(Venta.class)); // Verifica que se llame registrarVenta dos veces, una con un código viejo y otra con el nuevo.

        // Caso 3: Simulando una excepción de persistencia.
        doThrow(new PersistenciaException("Error de persistencia")).when(ventasDAO).registrarVenta(any(Venta.class));

        // Act & Assert
        assertThrows(ObjetosNegocioException.class, () -> ventaBO.registrarVenta(ventaDTO)); // Se asegura que se lanza una excepción.
    }

}

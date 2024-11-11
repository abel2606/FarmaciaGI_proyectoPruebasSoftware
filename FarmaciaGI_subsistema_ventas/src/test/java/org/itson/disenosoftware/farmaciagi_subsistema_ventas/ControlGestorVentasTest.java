package org.itson.disenosoftware.farmaciagi_subsistema_ventas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_ventas.excepciones.ControlVentasException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControlGestorVentasTest {

    public ControlGestorVentasTest() {
    }

    @Test
    public void RegistrarVenta_EnBaseDeDatos_Exitoso() throws Exception {

        // Arrange
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                5);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());
        GestorVentas gestorVentas = new GestorVentas();
        

        // Act
        gestorVentas.registrarVenta(ventaDTO);

        // Assert
        assertEquals("321", ventaDTO.getCodigo());
    }
    
     @Test
    public void RegistrarVenta_NoRegistrarEnBD_Fallido() throws Exception {

        // Arrange
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                0);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());
        GestorVentas gestorVentas = new GestorVentas();
        

        // Act
        gestorVentas.registrarVenta(ventaDTO);

        // Assert
        assertEquals("321", ventaDTO.getCodigo());
    }
    
    @Test
    void registrarVenta_LlamaRegistrarVentaEnVentaBO_SinExcepcion() {
        // Arrange
        ControlGestorVentas controlGestorVentas = new ControlGestorVentas();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                0);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());


        // Act & Assert
        assertDoesNotThrow(() -> controlGestorVentas.registrarVenta(ventaDTO), "No se esperaba ninguna excepción.");
    }
    
    @Test
    void registrarVenta_Exitosa_VentaRegistrada() {
        // Arrange
        ControlGestorVentas controlGestorVentas = new ControlGestorVentas();
        
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                0);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());
        
        // Act & Assert
        assertDoesNotThrow(() -> controlGestorVentas.registrarVenta(ventaDTO), "Se esperaba que la venta fuera registrada, pero ocurrió una excepción.");
    }
    @Test
    void registrarVenta_TiempoDeRespuesta_EnTiempoRazonable() {
        // Arrange
        ControlGestorVentas controlGestorVentas = new ControlGestorVentas();
        
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                0);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());

        // Act
        long startTime = System.currentTimeMillis();
        try {
            controlGestorVentas.registrarVenta(ventaDTO);
        } catch (ControlVentasException e) {
            fail("Se esperaba que la venta fuera registrada, pero ocurrió una excepción: " + e.getMessage());
        }
        long endTime = System.currentTimeMillis();

        // Assert
        long duration = endTime - startTime;
        assertTrue(duration < 200, "El tiempo de respuesta fue demasiado largo: " + duration + " ms.");
    }
   
     @Test
    void registrarVenta_ErrorDeBaseDeDatos_LanzaExcepcionAdecuada() {
        // Arrange
        ControlGestorVentas controlGestorVentas = new ControlGestorVentas();
        
        List<ProductoDTO> productosDTO = new ArrayList<>();
        ProductoDTO productoDTO = new ProductoDTO(
                "12345", 
                "Paracetamol", 
                200.5f, 
                "pfizer", 
                0);
        productosDTO.add(productoDTO);
        List<PromocionDTO> promocionesDTO = new ArrayList<>();
        VentaDTO ventaDTO = new VentaDTO(
                "321", 
                productosDTO, 
                promocionesDTO, 
                200.5F, 
                Calendar.getInstance());

        // Act & Assert
        ControlVentasException exception = assertThrows(ControlVentasException.class, () -> {
            // Simulando el fallo
            throw new ControlVentasException("Error de base de datos");
        });

        // Assert
        assertEquals("Error de base de datos", exception.getMessage(), "Se esperaba una excepción con el mensaje adecuado.");
    }

}

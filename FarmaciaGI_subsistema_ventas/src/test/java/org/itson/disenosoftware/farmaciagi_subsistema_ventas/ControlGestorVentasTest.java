package org.itson.disenosoftware.farmaciagi_subsistema_ventas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControlGestorVentasTest {

    public ControlGestorVentasTest() {
    }

    @Test
    public void RegistrarVenta_StockSuficiente_ActualizaInventario() throws Exception {

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

}

package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import java.time.Instant;
import java.util.ArrayList;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class VentasDAOTest {

    private VentasDAO ventasDAO;
    private final IConexion conexion;

    public VentasDAOTest() {
        conexion = new Conexion();
        ventasDAO = new VentasDAO(conexion);
    }

    @Test
    public void registrarVenta_RegistrarVentaCorrecta_VentaRegistrada() throws PersistenciaException {
        // Arrange
        Venta venta = new Venta(
                "123",
                new ArrayList<>(),
                new ArrayList<>(),
                200.5f,
                Instant.now());

        // Act
        Venta ventaRegistrada = ventasDAO.registrarVenta(venta);

        // Assert
        assertEquals("123", ventaRegistrada.getCodigoVenta());
    }

    @Test
    public void encontrarVenta_EncontrarVentaPorCodigo_VentaEncontrada() throws PersistenciaException {
        // Arrange
        Venta venta = new Venta(
                String.valueOf((int) (Math.random() * 1000000)),
                new ArrayList<>(),
                new ArrayList<>(),
                200.5f,
                Instant.now());
        // Act
        Venta ventaRegistrada = ventasDAO.registrarVenta(venta);
        Venta ventaEncontrada = ventasDAO.encontrarVenta(ventaRegistrada.getCodigoVenta());

        // Assert
        assertEquals(ventaRegistrada.getCodigoVenta(), ventaEncontrada.getCodigoVenta());
    }

    @Test
    public void registrarVenta_RegistrarVentaEnBd_EnMenosDeUnSegundo() throws PersistenciaException {
        // Arrange
        Venta venta = new Venta(
                String.valueOf((int) (Math.random() * 1000000)),
                new ArrayList<>(),
                new ArrayList<>(),
                200.5f,
                Instant.now());

        // Act
        long inicio = System.currentTimeMillis();
        ventasDAO.registrarVenta(venta);
        long fin = System.currentTimeMillis();

        // Assert
        long duracion = fin - inicio;
        assertTrue(duracion < 1000);
    }

    @Test
    public void encontrarVenta_BusquedaVentaPorCodigo_EnMenosDeUnSegundo() throws PersistenciaException {
       
        // Act
        long inicio = System.currentTimeMillis();
        ventasDAO.encontrarVenta("123");
        long fin = System.currentTimeMillis();

        // Assert
        long duracion = fin - inicio;
        assertTrue((duracion) < 1000);
        
    }

}

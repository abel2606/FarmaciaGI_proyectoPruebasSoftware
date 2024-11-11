
package org.itson.disenosofware.mifarmaciagi.persistencia.daos;


import java.time.Instant;
import java.util.ArrayList;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Compra;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Direccion;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ComprasDAOTest {

    private ComprasDAO comprasDAO;
    private final IConexion conexion;
    private Producto producto;
    private Proveedor proveedor;

    public ComprasDAOTest() {
        conexion = new Conexion();
        comprasDAO = new ComprasDAO(conexion);
        producto = new Producto( 
                "123", 
                "Ibuprofeno", 
                303.2f,
                "Similares", 
                7);
         proveedor = new Proveedor(
                "Ley",
                new Direccion(),
                new ArrayList(),
                "12345");
    }

    @Test
    public void registrarCompra_RegistrarCompraCorrecta_CompraRegistrada() throws PersistenciaException {
        // Arrange
        Compra compra = new Compra(
                String.valueOf(Math.random()), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());

        // Act
        Compra compraRegistrada = comprasDAO.registrarCompra(compra);

        // Assert
        assertEquals(compra.getCodigo(), compraRegistrada.getCodigo());
    }

    @Test
    public void registrarCompra_CompraYaExistente_LanzarPersistenciaException() throws PersistenciaException {
        // Arrange
        Compra compra1 = new Compra(
                String.valueOf(Math.random()), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());
       

       Compra compra2 = new Compra(
                compra1.getCodigo(), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());
       
       //Act
        comprasDAO.registrarCompra(compra1); 

        //Act & Assert
        PersistenciaException exception = assertThrows(PersistenciaException.class, () -> {
            comprasDAO.registrarCompra(compra2);
        });

        assertEquals("ERROR: Esta compra ya esta registrada", exception.getMessage());
    }

    @Test
    public void encontrarCompra_EncontrarCompraPorCodigo_CompraEncontrada() throws PersistenciaException {
        // Arrange
        Compra compra = new Compra(
                String.valueOf(Math.random()), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());
        

        // Act
        comprasDAO.registrarCompra(compra); 
        Compra compraEncontrada = comprasDAO.encontrarCompra(compra.getCodigo());

        // Assert
        assertEquals(compra.getCodigo(), compraEncontrada.getCodigo());
    }

    @Test
    public void encontrarCompra_CompraNoExistente_RetornarNull() {
        // Act
        Compra compraEncontrada = comprasDAO.encontrarCompra(String.valueOf(Math.random()));

        // Assert
        assertNull(compraEncontrada);
    }



    // No funcionales
    @Test
    public void registrarCompra_RegistrarCompraEnBd_EnMenosDeUnSegundo() throws PersistenciaException {
        // Arrange
         Compra compra = new Compra(
                String.valueOf(Math.random()), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());

        // Act
        long inicio = System.currentTimeMillis();
        comprasDAO.registrarCompra(compra);
        long fin = System.currentTimeMillis();

        // Assert
        long duracion = fin - inicio;
        assertTrue(duracion < 1000); 
    }

    @Test
    public void encontrarCompra_BuscarCompraPorCodigo_EnMenosDeUnSegundo() throws PersistenciaException {
        // Arrange
         Compra compra = new Compra(
                String.valueOf(Math.random()), 
                producto, 
                proveedor, 
                200.5f, 
                Instant.now());
        comprasDAO.registrarCompra(compra); 

        // Act
        long inicio = System.currentTimeMillis();
        comprasDAO.encontrarCompra("789");
        long fin = System.currentTimeMillis();

        // Assert
        long duracion = fin - inicio;
        assertTrue(duracion < 1000); 
    }
}



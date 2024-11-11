package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Compra;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Equipo 1
 */
public class CompraBOTest {

    private ProveedorDTO proveedor;
    private ProductoDTO producto;
    private CompraBO compraBO;
    
    public CompraBOTest() {
        proveedor = new ProveedorDTO(
                "Ley",
                new DireccionDTO(),
                new ArrayList(),
                "12345");
        producto = new ProductoDTO( 
                "123", 
                "Ibuprofeno", 
                303.2f,
                "Similares", 
                7);
        compraBO = new CompraBO();
   
    }
    
    

    @Test
    public void prueba_registrarCompra_CompraRegistradaExitosamente() throws ObjetosNegocioException {
        // Arrange
        CompraDTO compraNueva = new CompraDTO(
                "8025813",
                producto,
                proveedor,
                200.5f,
                new GregorianCalendar());

        // Act
        compraBO.registrarCompra(compraNueva);
        

        // Assert
        assertNotNull(compraBO.encontrarCompra("8025813"));
    }



    @Test
    public void prueba_generarCodigoCompra_CodigoGenerado() {
        // Arrange
        CompraBO compraBO = new CompraBO();

        // Act
        String codigo = compraBO.generarCodigoCompra();

        // Assert
        assertNotNull(codigo);
        assertEquals(7, codigo.length());
    }

    @Test
    public void prueba_productosDTOAProductosPOJO_ProductosConvertidos() {
        // Arrange
        List<ProductoDTO> productosDTO = new LinkedList<>();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setCodigo("P001");
        productoDTO.setNombre("Producto 1");
        productosDTO.add(productoDTO);

        CompraBO compraBO = new CompraBO();

        // Act
        List<Producto> productos = compraBO.productosDTOAProductosPOJO(productosDTO);

        // Assert
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("P001", productos.get(0).getCodigo());
    }

    @Test
    public void prueba_proveedoresDTOAProveedoresPOJO_ProveedoresConvertidos() {
        // Arrange
        List<ProveedorDTO> proveedoresDTO = new LinkedList<>();
        proveedoresDTO.add(proveedor);

        // Act
        List<Proveedor> proveedores = compraBO.proveedoresDTOAProveedoresPOJO(proveedoresDTO);

        // Assert
        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
        assertEquals("Ley", proveedores.get(0).getNombre());
    }

    @Test
    public void prueba_encontrarCompra_CompraEncontrada() {
        // Act
        Compra compra = compraBO.encontrarCompra("8305778");

        // Assert
        assertNotNull(compra);
        assertEquals("8305778", compra.getCodigo());
    }

    @Test
    public void prueba_registrarCompra_TiempoDeEjecucion() throws ObjetosNegocioException {
        //Arrange
        CompraDTO compraNueva = new CompraDTO(
                "8025813",
                producto,
                proveedor,
                200.5f,
                new GregorianCalendar());
        
        // Act
        long startTime = System.currentTimeMillis();
        compraBO.registrarCompra(compraNueva);
        long endTime = System.currentTimeMillis();

        // Assert
        long duration = endTime - startTime;
        assertTrue(duration < 1000);  // Tiempo de ejecución debe ser menor a 1 segundo
    }

    @Test
    public void prueba_encontrarProveedores_RendimientoBajoCarga() throws ObjetosNegocioException {
        // Arrange
        int numRequests = 1000;

        // Act
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numRequests; i++) {
            compraBO.encontrarProveedores(producto);
        }
        long endTime = System.currentTimeMillis();

        // Assert
        long duration = endTime - startTime;
        assertTrue(duration < 10000);  
    }

    @Test
    public void prueba_generarCodigoCompra_RendimientoBajoCarga() {
        // Arrange
        int numGeneraciones = 10000;

        // Act
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numGeneraciones; i++) {
            compraBO.generarCodigoCompra();
        }
        long endTime = System.currentTimeMillis();

        // Assert
        long duration = endTime - startTime;
        assertTrue(duration < 5000); 
    }



    @Test
    public void prueba_encontrarProveedores_CoberturaDeRutas() throws ObjetosNegocioException {
        // Arrange
        CompraBO compraBO = new CompraBO();

        // Act
        List<ProveedorDTO> proveedores = compraBO.encontrarProveedores(producto);

        // Assert
        assertNotNull(proveedores);
    }

}

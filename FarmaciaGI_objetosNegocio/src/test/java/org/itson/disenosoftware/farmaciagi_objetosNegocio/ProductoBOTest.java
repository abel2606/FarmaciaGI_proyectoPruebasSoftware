/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.IProductosDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.ProductosDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

/**
 *
 * @author Abe
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoBOTest {

    private IProductoBO productoBO;
//    private IProductosDAO productosDAO;
//    private IConexion conexion;

    @BeforeEach
    public void setUp() throws ObjetosNegocioException {
//        conexion = new Conexion();
//        productosDAO = new ProductosDAO(conexion);
        productoBO = new ProductoBO();

    }

    @Test
    @Order(1)
    public void obtenerProducto_ProductoExistenteEnBaseDeDatos_ReturnProductoDTO() {
        // Arrange
        ProductoDTO productoEsperado = new ProductoDTO();
        productoEsperado.setCodigo("PRC-001");
        productoEsperado.setNombre("Paracetamol");
        productoEsperado.setMarca("Pfizer");
        productoEsperado.setCosto(100.0f);
        productoEsperado.setCantidad(10);

        // Primero registramos el producto para asegurar que existe
        try {
            productoBO.registrarProducto(productoEsperado);
        } catch (ObjetosNegocioException e) {
            fail("No se pudo preparar el test: " + e.getMessage());
        }

        // Act
        ProductoDTO productoObtenido = productoBO.obtenerProducto(productoEsperado);

        // Assert
        assertNotNull(productoObtenido);
        assertEquals(productoEsperado.getCodigo(), productoObtenido.getCodigo());
        assertEquals(productoEsperado.getNombre(), productoObtenido.getNombre());
        assertEquals(productoEsperado.getMarca(), productoObtenido.getMarca());
    }

    @Test
    @Order(2)
    public void obtenerProducto_ProductoNoExistente_ReturnNull() {
        // Arrange
        ProductoDTO productoInexistente = new ProductoDTO();
        productoInexistente.setCodigo("NOEXISTE123");

        // Act
        ProductoDTO resultado = productoBO.obtenerProducto(productoInexistente);

        // Assert
        assertNull(resultado, "Debería retornar null para un producto que no existe");
    }

    @Test
    @Order(3)
    public void registrarProducto_ProductoNuevoValido_ReturnProductoRegistrado() throws ObjetosNegocioException {
        // Arrange
        ProductoDTO productoNuevo = new ProductoDTO();
        productoNuevo.setCodigo("IBU-323");
        productoNuevo.setNombre("Ibuprofeno");
        productoNuevo.setMarca("");
        productoNuevo.setCosto(150.0f);

        // Act
        ProductoDTO productoRegistrado = productoBO.registrarProducto(productoNuevo);

        // Assert
        assertNotNull(productoRegistrado);
        assertEquals(productoNuevo.getCodigo(), productoRegistrado.getCodigo());
    }

    @Test
    @Order(4)
    public void registrarProducto_CodigoExistente_ThrowObjetosNegocioException() {
        // Arrange
        ProductoDTO productoExistente = new ProductoDTO();
        productoExistente.setCodigo("IBU-323");
        productoExistente.setNombre("Producto Test");
        productoExistente.setCosto(100.0f);

        // Act & Assert
        assertThrows(ObjetosNegocioException.class, () -> {
            productoBO.registrarProducto(productoExistente);
        });
    }

    @Test
    @Order(5)
    public void asignarProveedor_ProveedorValidoAProductoExistente_AsignacionExitosa() {
        // Arrange
        ProductoDTO producto = new ProductoDTO();
        producto.setCodigo("IBU-323");
        producto.setNombre("Ibuprofeno");
        producto.setMarca("");
        producto.setCosto(150.0f);

        List<String> telefonos = new LinkedList<>();
        telefonos.add("64644744");
        telefonos.add("45555");
        ProveedorDTO proveedor = new ProveedorDTO("Pfizer", new DireccionDTO(), telefonos, "ABDD123456ABC");

        ProveedorBO proveedorBO = new ProveedorBO();

        try {

            ProductoDTO productoBuscado = productoBO.obtenerProducto(producto);
            proveedorBO.registrarProveedor(proveedor);

            // Act
            productoBO.asignarProveedor(productoBuscado, proveedor);

            // Assert
            ProductoDTO productoActualizado = productoBO.obtenerProducto(producto);
            assertNotNull(productoActualizado);
        } catch (ObjetosNegocioException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    // Prueba estructural (cobertura de caminos)
    @Test
    @Order(6)
    public void asignarProveedor_ProductoNoExistente_ThrowObjetosNegocioException() {
        // Arrange
        ProductoDTO productoInexistente = new ProductoDTO();
        productoInexistente.setCodigo("NOEXISTE123");
        ProveedorDTO proveedor = new ProveedorDTO();
        proveedor.setRfc("PROV123");

        // Act & Assert
        assertThrows(ObjetosNegocioException.class, () -> {
            productoBO.asignarProveedor(productoInexistente, proveedor);
        }, "Debería lanzar excepción al intentar asignar proveedor a producto inexistente");
    }

    @Test
    @Order(7)
    public void buscarProductosPorNombre_ProductosExistentes_ReturnListaProductos() {
        // Arrange
        String nombreBusqueda = "Ibuprofeno";

        // Act
        List<ProductoDTO> productosEncontrados = productoBO.buscarProductosPorNombre(nombreBusqueda);

        // Assert
        assertNotNull(productosEncontrados);
        assertTrue(!productosEncontrados.isEmpty());
    }

    @Test
    @Order(8)
    public void buscarProductosPorNombre_NombreVacio_ReturnListaVacia() {
        // Arrange
        String nombreBusqueda = " ";

        // Act
        List<ProductoDTO> resultados = productoBO.buscarProductosPorNombre(nombreBusqueda);

        // Assert
        assertTrue(resultados.isEmpty());
    }

    // Prueba no funcional (rendimiento)
    @Test
    @Order(9)
    public void buscarProductosPorNombre_RendimientoConProductos_CompletarEnMenosDe2Segundos() {
        // Arrange
        String nombreBusqueda = "Ibuprofeno";
        long tiempoInicio = System.currentTimeMillis();

        // Act
        List<ProductoDTO> resultados = productoBO.buscarProductosPorNombre(nombreBusqueda);
        long tiempoFin = System.currentTimeMillis();

        // Assert
        long tiempoTotal = tiempoFin - tiempoInicio;
        assertTrue(tiempoTotal < 2000, "La búsqueda debería completarse en menos de 2 segundos");
    }

    @Test
    @Order(10)
    public void modCantidadProducto_ProductoExistente_ActualizacionExitosa() {
        // Arrange
        ProductoDTO producto = new ProductoDTO();
        producto.setCodigo("IBU-323");
        producto.setCantidad(20);

        try {

            // Act
            producto.setCantidad(30);
            productoBO.modCantidadProducto(producto);

            // Assert
            ProductoDTO productoActualizado = productoBO.obtenerProducto(producto);
            assertNotNull(productoActualizado);
            assertEquals(Integer.valueOf(30), productoActualizado.getCantidad());

        } catch (ObjetosNegocioException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    // Prueba no funcional (robustez)
    @Test
    @Order(11)
    public void modCantidadProducto_CantidadNegativa_ThrowObjetosNegocioException() {
        // Arrange
        ProductoDTO producto = new ProductoDTO();
        producto.setCodigo("IBU-323");
        producto.setCantidad(-10000);

        // Act y Assert
        assertThrows(ObjetosNegocioException.class, () -> {
            productoBO.modCantidadProducto(producto);
        }, "Debería lanzar excepción al intentar establecer cantidad negativa");
    }

    @Test
    @Order(12)
    public void actualizarProducto_ProductoValido_ActualizacionExitosa() {
        // Arrange
        ProductoDTO producto = new ProductoDTO();
        producto.setCodigo("IBU-323");
        producto.setNombre("Ibuprofeno");
        producto.setMarca("Sanofi");
        producto.setCosto(200.0f);

        // Act & Assert
        assertDoesNotThrow(() -> {
            productoBO.actualizarProducto(producto);
        });
    }

    // Prueba estructural (validación de estado)
    @Test
    @Order(13)
    public void actualizarProducto_ProductoSinCodigo_ThrowObjetosNegocioException() {
        // Arrange
        ProductoDTO productoSinCodigo = new ProductoDTO();
        productoSinCodigo.setNombre("Test");
        productoSinCodigo.setMarca("Test");
        productoSinCodigo.setCosto(100.0f);

        // Act & Assert
        assertThrows(ObjetosNegocioException.class, () -> {
            productoBO.actualizarProducto(productoSinCodigo);
        }, "Debería lanzar excepción al intentar actualizar producto sin código");
    }

    @Test
    @Order(14)
    public void obtenerInventario_InventarioNoVacio_ReturnListaCompleta() {
        // Arrange
        List<ProductoDTO> inventario;

        //Act
        inventario = productoBO.obtnerInventario();

        // Assert
        assertNotNull(inventario, "El inventario no debería ser null");
        assertTrue(inventario.size() > 0, "El inventario no debería estar vacío");
    }

}

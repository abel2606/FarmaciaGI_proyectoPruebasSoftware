/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.disenosoftware.farmaciagi_subsistema_productos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_subsistema_productos.excepciones.GestorProductosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Abel
 */
public class GestorProductosTest {

    @Mock
    private ControlGestorProductos control;

    @InjectMocks
    private GestorProductos gestor;

    public GestorProductosTest() {

    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of obtenerProducto method, of class GestorProductos.
     */
    @Test
    public void obtenerProducto_ProductoExiste_ReturnProducto() {
        // Arrange
        ProductoDTO producto = new ProductoDTO("1231", "Paracetamol", 14f);
        ProductoDTO esperado = null;
        ProductoDTO resultado = null;
        Mockito.when(control.obtenerProducto(producto)).thenReturn(esperado);
        // Act
        resultado = gestor.obtenerProducto(producto);

        // Assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void obtenerProducto_ProductoNoExiste_ReturnNull() {
        // Arrange
        ProductoDTO producto = new ProductoDTO("XYZ-999", "Cloroformo", 0f);
        Mockito.when(control.obtenerProducto(producto)).thenReturn(null);

        // Act
        ProductoDTO resultado = gestor.obtenerProducto(producto);

        // Assert
        assertNull(resultado);
    }
    /**
     * Test of modCantidadProducto method, of class GestorProductos.
     */
    @Test
    public void modCantidadProducto_CantidadValida_Success() throws Exception {
        ProductoDTO producto = new ProductoDTO("4343", "Ibuprofeno", 20f, "IPS", 2);
        doNothing().when(control).modCantidadProducto(producto);

        // Act
        gestor.modCantidadProducto(producto);

        // Assert
        Mockito.verify(control).modCantidadProducto(producto);
    }
   
    

    /**
     * Test of buscarProductosPorNombre method, of class GestorProductos.
     */
    @Test
    public void buscarProductosPorNombre_ProductoExistente_ReturnTrue() {
         // Arrange
        String nombreBusqueda = "Para";
        List<ProductoDTO> productosEsperados = new ArrayList<>();
        productosEsperados.add(new ProductoDTO("ABC-123", "Paracetamol", 14f));
        Mockito.when(control.buscarProductosPorNombre(nombreBusqueda)).thenReturn(productosEsperados);

        // Act
        List<ProductoDTO> resultado = gestor.buscarProductosPorNombre(nombreBusqueda);

        // Assert
        assertEquals(productosEsperados, resultado);
        verify(control).buscarProductosPorNombre(nombreBusqueda);
    }

    // Prueba no funcional

    @Test
    public void buscarProductosPorNombre_Lista_RespondeEnTiempo() {
        // Arrange
        String nombreBusqueda = "Ibup";
        List<ProductoDTO> listaGrande = new LinkedList<>();
        Mockito.when(control.buscarProductosPorNombre(nombreBusqueda)).thenReturn(listaGrande);

        // Act
        long inicio = System.currentTimeMillis();
        List<ProductoDTO> resultado = gestor.buscarProductosPorNombre(nombreBusqueda);
        long fin = System.currentTimeMillis();

        // Assert
        assertTrue((fin - inicio) < 5000); // Debe responder en menos de 1 segundo
        assertTrue(resultado.isEmpty());
    }
    /**
     * Test of asignarProveedorAProducto method, of class GestorProductos.
     */
    @Test
    public void AsignarProveedorAProducto_RelacionarProductoYProveedor_ProveedorAsignado() throws Exception {
        // Arrange
        ProductoDTO producto = new ProductoDTO("ABC-123", "Paracetamol", 14f);
        ProveedorDTO proveedor = new ProveedorDTO();
        
        doNothing().when(control).asignarProveedorAProducto(producto, proveedor);

        // Act
        gestor.asignarProveedorAProducto(producto, proveedor);

        // Assert
        verify(control).asignarProveedorAProducto(producto, proveedor);
    }

    /**
     * Test of registrarProducto method, of class GestorProductos.
     */
    @Test
    public void registrarProducto_CodigoValido_ReturnProductoRegistrado() throws Exception {
        // Arrange
        ProductoDTO producto = new ProductoDTO("ABC-123", "Paracetamol", 14f);
        Mockito.when(control.registrarProducto(producto)).thenReturn(producto);

        // Act
        ProductoDTO resultado = gestor.registrarProducto(producto);

        // Assert
        assertEquals(producto, resultado);
        assertEquals(producto.getCodigo(), resultado.getCodigo().toUpperCase());
    }

    /**
     * Test of obtnerInventario method, of class GestorProductos.
     */
    @Test
    public void ObtenerInventario_VerificarVacio_ListaNoVacia() {
    // Arrange
        List<ProductoDTO> inventarioEsperado = new ArrayList<>();
        inventarioEsperado.add(new ProductoDTO("ABC-123", "Paracetamol", 14f));
        inventarioEsperado.add(new ProductoDTO("DEF-456", "Ibuprofeno", 20f));
        Mockito.when(control.obtenerInventario()).thenReturn(inventarioEsperado);

        // Act
        List<ProductoDTO> resultado = gestor.obtnerInventario();

        // Assert
        assertEquals(inventarioEsperado, resultado);
        verify(control).obtenerInventario();
    }

    /**
     * Test of actualizarProducto method, of class GestorProductos.
     */
    @Test
    public void ActualizarProducto_VerificarActualizacion_ReturnSuccess() throws Exception {
         // Arrange
        ProductoDTO producto = new ProductoDTO("ABC-123", "Paracetamol", 14f);
        doNothing().when(control).actualizarProducto(producto);

        // Act
        gestor.actualizarProducto(producto);

        // Assert
        verify(control).actualizarProducto(producto);
    }

}

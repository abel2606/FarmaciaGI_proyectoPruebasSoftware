/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.disenosoftware.farmaciagi_subsistema_productos;

import java.util.List;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
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
 * @author Abe
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
    public void testObtenerProducto() {
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

    /**
     * Test of modCantidadProducto method, of class GestorProductos.
     */
    @Test
    public void testModCantidadProducto() throws Exception {
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
    public void testBuscarProductosPorNombre() {
        System.out.println("buscarProductosPorNombre");
        String nombre = "";
        GestorProductos instance = new GestorProductos();
        List<ProductoDTO> expResult = null;
        List<ProductoDTO> result = instance.buscarProductosPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignarProveedorAProducto method, of class GestorProductos.
     */
    @Test
    public void testAsignarProveedorAProducto() throws Exception {
        System.out.println("asignarProveedorAProducto");
        ProductoDTO productoDTO = null;
        ProveedorDTO proveedorDTO = null;
        GestorProductos instance = new GestorProductos();
        instance.asignarProveedorAProducto(productoDTO, proveedorDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarProducto method, of class GestorProductos.
     */
    @Test
    public void testRegistrarProducto() throws Exception {
        System.out.println("registrarProducto");
        ProductoDTO productoDTO = null;
        GestorProductos instance = new GestorProductos();
        ProductoDTO expResult = null;
        ProductoDTO result = instance.registrarProducto(productoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtnerInventario method, of class GestorProductos.
     */
    @Test
    public void testObtnerInventario() {
        System.out.println("obtnerInventario");
        GestorProductos instance = new GestorProductos();
        List<ProductoDTO> expResult = null;
        List<ProductoDTO> result = instance.obtnerInventario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarProducto method, of class GestorProductos.
     */
    @Test
    public void testActualizarProducto() throws Exception {
        System.out.println("actualizarProducto");
        ProductoDTO producto = null;
        GestorProductos instance = new GestorProductos();
        instance.actualizarProducto(producto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

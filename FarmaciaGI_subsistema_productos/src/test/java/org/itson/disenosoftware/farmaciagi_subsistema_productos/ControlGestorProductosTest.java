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

/**
 *
 * @author Abe
 */
public class ControlGestorProductosTest {
    private ControlGestorProductos control;
    private ProductoDTO producto;
    public ControlGestorProductosTest() {
        control = new ControlGestorProductos();
        producto = new ProductoDTO("1234", "Paracetamol", 14f);
    }

    /**
     * Test of obtenerProducto method, of class ControlGestorProductos.
     */
    @Test
    public void testObtenerProducto() {
        //Assert
        ProductoDTO esperado = new ProductoDTO("1234", "Paracetamol", 14f);
        ProductoDTO resultado = null;
        //Act
        resultado = control.obtenerProducto(producto);
        
        //Arrange
        assertEquals(esperado, resultado);
    }

    /**
     * Test of modCantidadProducto method, of class ControlGestorProductos.
     */
    @Test
    public void testModCantidadProducto() throws Exception {
        System.out.println("modCantidadProducto");
        ProductoDTO productoActualizado = null;
        ControlGestorProductos instance = new ControlGestorProductos();
        instance.modCantidadProducto(productoActualizado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarProductosPorNombre method, of class ControlGestorProductos.
     */
    @Test
    public void testBuscarProductosPorNombre() {
        System.out.println("buscarProductosPorNombre");
        String nombre = "";
        ControlGestorProductos instance = new ControlGestorProductos();
        List<ProductoDTO> expResult = null;
        List<ProductoDTO> result = instance.buscarProductosPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignarProveedorAProducto method, of class ControlGestorProductos.
     */
    @Test
    public void testAsignarProveedorAProducto() throws Exception {
        System.out.println("asignarProveedorAProducto");
        ProductoDTO productoDTO = null;
        ProveedorDTO proveedorDTO = null;
        ControlGestorProductos instance = new ControlGestorProductos();
        instance.asignarProveedorAProducto(productoDTO, proveedorDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarProducto method, of class ControlGestorProductos.
     */
    @Test
    public void testRegistrarProducto() throws Exception {
        System.out.println("registrarProducto");
        ProductoDTO productoDTO = null;
        ControlGestorProductos instance = new ControlGestorProductos();
        ProductoDTO expResult = null;
        ProductoDTO result = instance.registrarProducto(productoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerInventario method, of class ControlGestorProductos.
     */
    @Test
    public void testObtenerInventario() {
        System.out.println("obtenerInventario");
        ControlGestorProductos instance = new ControlGestorProductos();
        List<ProductoDTO> expResult = null;
        List<ProductoDTO> result = instance.obtenerInventario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarProducto method, of class ControlGestorProductos.
     */
    @Test
    public void testActualizarProducto() throws Exception {
        System.out.println("actualizarProducto");
        ProductoDTO productoDTO = null;
        ControlGestorProductos instance = new ControlGestorProductos();
        instance.actualizarProducto(productoDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

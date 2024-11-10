/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Abe
 */
@ExtendWith(MockitoExtension.class)
public class ProductosDAOTest {

    @Mock
    private IProductosDAO productosDAO;

    @Test
    public void RegistrarProducto_ProductoNuevo_ReturnSuccess() throws Exception {
        // arrange
        Producto productoNuevo = new Producto("Paracetamol", 25.0f, "PAR-001");
        when(productosDAO.registrarProducto(productoNuevo)).thenReturn(productoNuevo);
        Producto resultado;

        // act
        resultado = productosDAO.registrarProducto(productoNuevo);

        // assert
        assertEquals(productoNuevo, resultado);
        verify(productosDAO, Mockito.times(1)).registrarProducto(productoNuevo);
    }

    @Test
    public void RegistrarProducto_ProductoExistente_ThrowException() throws PersistenciaException {
        // arrange
        Producto productoExistente = new Producto("Paracetamol", 25.0f, "PAR-001");
        when(productosDAO.registrarProducto(productoExistente)).thenThrow(new PersistenciaException("Error: El producto ya existe"));
        PersistenciaException e = null;

        // act
        try {
            productosDAO.registrarProducto(productoExistente);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        // assert
        assertNotNull(e);
        assertEquals("Error: El producto ya existe", e.getMessage());
    }

    @Test
    public void ActualizarProducto_ProductoExistente_ReturnSuccess() throws Exception {
        // arrange
        Producto productoActualizar = new Producto("Paracetamol Plus", 30.0f, "PAR-001");
        when(productosDAO.actualizarProducto(productoActualizar)).thenReturn(productoActualizar);
        Producto resultado;

        // act
        resultado = productosDAO.actualizarProducto(productoActualizar);

        // assert
        assertEquals(productoActualizar, resultado);
        verify(productosDAO, Mockito.times(1)).actualizarProducto(productoActualizar);
    }

    @Test
    public void ActualizarProducto_ProductoInexistente_ThrowException() throws PersistenciaException {
        // arrange
        Producto productoInexistente = new Producto("NoExiste", 10.0f, "XXX-001");
        when(productosDAO.actualizarProducto(productoInexistente))
                .thenThrow(new PersistenciaException("Error: El producto no se encuentra en inventario"));
        PersistenciaException e = null;

        // act
        try {
            productosDAO.actualizarProducto(productoInexistente);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        // assert
        assertNotNull(e);
        assertEquals("Error: El producto no se encuentra en inventario", e.getMessage());
    }
    @Test
    public void ModCantidadProducto_IncrementarCantidad_ReturnSuccess() throws Exception {
        // arrange
        Producto producto = new Producto("Paracetamol", 25.0f, "PAR-001");
        producto.setCantidad(5);
        doNothing().when(productosDAO).modCantidadProducto(producto);

        // act
        productosDAO.modCantidadProducto(producto);

        // assert
        verify(productosDAO, Mockito.times(1)).modCantidadProducto(producto);
    }

    @Test
    public void ModCantidadProducto_CantidadInsuficiente_ThrowException() throws PersistenciaException {
        // arrange
        Producto producto = new Producto("Paracetamol", 25.0f, "PAR-001");
        producto.setCantidad(-10);
        doThrow(new PersistenciaException("Error: Cantidad insuficiente de stock"))
                .when(productosDAO).modCantidadProducto(producto);
        PersistenciaException e = null;

        // act
        try {
            productosDAO.modCantidadProducto(producto);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        // assert
        assertNotNull(e);
        assertEquals("Error: Cantidad insuficiente de stock", e.getMessage());
    }

    @Test
    public void BuscarProductosPorNombre_ProductosExistentes_ReturnSuccess() {
        // arrange
        List<Producto> productosEsperados = new LinkedList<>();
        productosEsperados.add(new Producto("Paracetamol", 25.0f, "PAR-001"));
        productosEsperados.add(new Producto("Paracetamol Plus", 30.0f, "PAR-002"));
        when(productosDAO.buscarProductosPorNombre(any(Producto.class))).thenReturn(productosEsperados);
        List<Producto> resultado;

        // act
        resultado = productosDAO.buscarProductosPorNombre(new Producto("Paracetamol", 0.0f, ""));

        // assert
        assertEquals(productosEsperados.size(), resultado.size());
        assertEquals(productosEsperados, resultado);
    }

    @Test
    public void AsignarProveedor_ProveedorNuevo_ReturnSuccess() throws Exception {
        // arrange
        Producto producto = new Producto("Paracetamol", 25.0f, "PAR-001");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(new ObjectId());
        doNothing().when(productosDAO).asignarProveedor(producto, proveedor);

        // act
        productosDAO.asignarProveedor(producto, proveedor);

        // assert
        verify(productosDAO, Mockito.times(1)).asignarProveedor(producto, proveedor);
    }

    @Test
    public void AsignarProveedor_ProveedorYaAsignado_ThrowException() throws PersistenciaException {
        // arrange
        Producto producto = new Producto("Paracetamol", 25.0f, "PAR-001");
        Proveedor proveedor = new Proveedor();
        proveedor.setId(new ObjectId());
        doThrow(new PersistenciaException("Error: Al producto ya se le asigno este proveedor"))
                .when(productosDAO).asignarProveedor(producto, proveedor);
        PersistenciaException e = null;

        // act
        try {
            productosDAO.asignarProveedor(producto, proveedor);
        } catch (PersistenciaException ex) {
            e = ex;
        }

        // assert
        assertNotNull(e);
        assertEquals("Error: Al producto ya se le asigno este proveedor", e.getMessage());
    }

    @Test
    public void ObtenerInventario_ListaProductos_ReturnSuccess() {
        // arrange
        List<Producto> productosEsperados = new LinkedList<>();
        productosEsperados.add(new Producto("Paracetamol", 25.0f, "PAR-001"));
        productosEsperados.add(new Producto("Ibuprofeno", 30.0f, "IBU-001"));
        when(productosDAO.obtenerInventario()).thenReturn(productosEsperados);
        List<Producto> resultado;

        // act
        resultado = productosDAO.obtenerInventario();

        // assert
        assertEquals(productosEsperados.size(), resultado.size());
        assertEquals(productosEsperados, resultado);
    }
}

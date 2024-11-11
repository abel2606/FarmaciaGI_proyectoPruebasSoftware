package com.mycompany.farmaciagi_subsistema_compra;

import com.mycompany.farmaciagi_subsistema_compra_excepciones.ControlCompraProductosException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.ICompraBO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

class ControlCompraProductosTest {

    @Mock
    private ICompraBO compraMock;

    private ProveedorDTO proveedor;
    private ProductoDTO producto;

    @InjectMocks
    private ControlCompraProductos controlCompraProductos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Inicialización de los objetos
        proveedor = new ProveedorDTO(
                "Ley",
                new DireccionDTO(),
                new ArrayList<>(),
                "12345");

        producto = new ProductoDTO(
                "123",
                "Paracetamol",
                100.5f,
                "Pfizer",
                3);

    }

    @Test
    void registrarCompra_LlamadoCorrecto_SinExcepcion() throws ObjetosNegocioException {
        // Arrange
        CompraDTO compra = new CompraDTO(
                "123", producto, proveedor, 300f, new GregorianCalendar());

        // Act & Assert
        assertDoesNotThrow(() -> controlCompraProductos.registrarCompra(compra));
    }

    @Test
    void registrarCompra_LanzarExcepcionControlada_ControlCompraProductosException() throws ObjetosNegocioException {
        // Arrange
        CompraDTO compraNueva = new CompraDTO();
        doThrow(new ObjetosNegocioException("ExcepcionTest")).when(compraMock).registrarCompra(compraNueva);

        // Act & Assert
        assertThrows(ControlCompraProductosException.class, () -> controlCompraProductos.registrarCompra(compraNueva));

    }

    @Test
    void encontrarProveedores_LlamadoCorrecto_ListaProveedores() throws ControlCompraProductosException, ObjetosNegocioException {
        // Arrange
        ProductoDTO productoNuevo = new ProductoDTO();
        List<ProveedorDTO> proveedores = new ArrayList<>();
        proveedores.add(new ProveedorDTO());
        // Act
        when(compraMock.encontrarProveedores(productoNuevo)).thenReturn(proveedores);
        List<ProveedorDTO> resultado = controlCompraProductos.encontrarProveedores(productoNuevo);
        // Assert
        assertEquals(proveedores, resultado);
    }

    @Test
    void registrarCompra_CompraValida_RegistroExitoso() {
        //Arrange
        CompraDTO compraNueva = new CompraDTO();
        //Act & Assert
        assertDoesNotThrow(() -> controlCompraProductos.registrarCompra(compraNueva));
    }

    @Test
    void encontrarProveedores_ProductoConProveedores_ListaProveedores() throws ControlCompraProductosException, ObjetosNegocioException {
        // Arrange
        ProductoDTO productoNuevo = new ProductoDTO();
        List<ProveedorDTO> proveedoresEsperados = new ArrayList<>();
        proveedoresEsperados.add(new ProveedorDTO());

        // Act
        when(compraMock.encontrarProveedores(productoNuevo)).thenReturn(proveedoresEsperados);
        List<ProveedorDTO> proveedores = controlCompraProductos.encontrarProveedores(productoNuevo);

        // Assert
        assertEquals(proveedoresEsperados, proveedores);
    }

    @Test
    void encontrarProveedores_ProductoSinProveedores_ControlCompraProductosException() throws ObjetosNegocioException {
        //Arrange
        ProductoDTO productoSinProveedores = new ProductoDTO();
        //Act
        doThrow(new ObjetosNegocioException("ExcepcionTest")).when(compraMock).encontrarProveedores(productoSinProveedores);
        //Assert
        assertThrows(ControlCompraProductosException.class, () -> controlCompraProductos.encontrarProveedores(productoSinProveedores));
    }

    @Test
    void registrarCompra_AltaFrecuenciaLlamados_TiempoAceptable() {
        //Arrange
        CompraDTO compraNueva = new CompraDTO();
        //Act & Assert
        assertTimeout(Duration.ofSeconds(5), () -> {
            for (int i = 0; i < 1000; i++) {
                controlCompraProductos.registrarCompra(compraNueva);
            }
        });
    }

    @Test
    void encontrarProveedores_AltaCantidadProveedores_TiempoAceptable() throws ControlCompraProductosException, ObjetosNegocioException {
        // Arrange
        ProductoDTO productoNuevo = new ProductoDTO();
        List<ProveedorDTO> proveedores = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            proveedores.add(new ProveedorDTO());
        }

        when(compraMock.encontrarProveedores(productoNuevo)).thenReturn(proveedores);

        // Act & Assert
        assertTimeout(Duration.ofSeconds(5), () -> {
            controlCompraProductos.encontrarProveedores(productoNuevo);
        });
    }
}

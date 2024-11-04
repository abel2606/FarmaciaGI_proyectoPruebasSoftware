/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.diseñosoftware.farmaciagi_objetosNegocio;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ComprasDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IComprasDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Compra;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Direccion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;
import org.itson.disenosoftware.farmaciagi_dtos.CompraDTO;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

/**
 * Clase de Compras BO, esta clase maneja los objetos negocio que conforman a una
 * compra
 * 
 * @author Enrique Rodriguez
 */
public class CompraBO implements ICompraBO{

    private IComprasDAO comprasDAO;

    public CompraBO() {
        IConexion conexion = new Conexion();
        comprasDAO = new ComprasDAO(conexion);
    }

    /**
     * Método para registrar una compra
     *
     * @param compraNueva compra a registrar.
     *
     * @throws ObjetosNegocioException En caso de no poder agregar
     */
    @Override
    public void registrarCompra(CompraDTO compraNueva) throws ObjetosNegocioException {
        Compra compra = null;
        String codigo;

        do {
            codigo = generarCodigoCompra();
            compra = encontrarCompra(codigo);
        } while (compra != null);

        compra = new Compra();
        compra.setCodigo(codigo);
        compra.setCostoTotal(compraNueva.getCostoTotal());
        long millis = compraNueva.getFecha().getTimeInMillis();
        compra.setFecha(Instant.ofEpochMilli(millis));

        //Establecer los valores del proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(compraNueva.getProveedor().getNombre());
        proveedor.setTelefonos(compraNueva.getProveedor().getTelefonos());
        proveedor.setRfc(compraNueva.getProveedor().getRfc());

        //Establecer los valores de dirección
        Direccion direccion = new Direccion();
        direccion.setCalle(compraNueva.getProveedor().getDireccion().getCalle());
        direccion.setCiudad(compraNueva.getProveedor().getDireccion().getCiudad());
        direccion.setCodigo_postal(compraNueva.getProveedor().getDireccion().getCodigo_postal());
        direccion.setColonia(compraNueva.getProveedor().getDireccion().getColonia());
        direccion.setNumero(compraNueva.getProveedor().getDireccion().getNumero());
        proveedor.setDireccion(direccion);

        compra.setProveedor(proveedor);

        Producto producto = new Producto();
        producto.setCantidad(compraNueva.getProducto().getCantidad());
        producto.setCodigo(compraNueva.getProducto().getCodigo());
        producto.setCosto(compraNueva.getProducto().getCosto());
        producto.setMarca(compraNueva.getProducto().getMarca());
        producto.setNombre(compraNueva.getProducto().getNombre());

        compra.setProducto(producto);

        try {
            comprasDAO.registrarCompra(compra);
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException("No se pudo registrar la compra.");
        }
    }
    
    /**
     * Método para encontrar una serie de proveedores asociados a un producto
     * 
     * @param productoDTO producto a evaluar.
     * @return lista de proveedores
     * @throws ObjetosNegocioException 
     */
    @Override
    public List<ProveedorDTO> encontrarProveedores(ProductoDTO productoDTO) throws ObjetosNegocioException {
        Producto producto = new Producto();
        producto.setCodigo(productoDTO.getCodigo());
        try {
            List<Proveedor> proveedores = comprasDAO.encontrarProveedores(producto);
            List<ProveedorDTO> proveedoresDTO = new LinkedList<>();
            for (Proveedor proveedor : proveedores) {
                //Asignar la direccion a direccionDTO
                DireccionDTO direccion = new DireccionDTO();
                direccion.setCalle(proveedor.getDireccion().getCalle());
                direccion.setCiudad(proveedor.getDireccion().getCiudad());
                direccion.setCodigo_postal(proveedor.getDireccion().getCodigo_postal());
                direccion.setColonia(proveedor.getDireccion().getColonia());
                direccion.setNumero(proveedor.getDireccion().getNumero());

                //Asignar los valores
                ProveedorDTO proveedorDTO = new ProveedorDTO();
                proveedorDTO.setNombre(proveedor.getNombre());
                proveedorDTO.setRfc(proveedor.getRfc());
                proveedorDTO.setTelefonos(proveedor.getTelefonos());
                proveedorDTO.setDireccion(direccion);

                //Agregar el proveedor DTO creado
                proveedoresDTO.add(proveedorDTO);
            }
            return proveedoresDTO;
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException("No se pudo encontrar ningun proveedor.");
        }

    }

    /**
     * Permite generar un código para una compra.
     *
     * @return código generado
     */
    private String generarCodigoCompra() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int numeroAleatorio = random.nextInt(10);
            codigo.append(numeroAleatorio);
        }
        return codigo.toString();
    }

    /**
     * Permite convertir una lista de Producto DTO a Producto POJO
     *
     * @param productosDTO La lista de Producto DTO
     * @return La lista de Producto POJO
     */
    private List<Producto> productosDTOAProductosPOJO(List<ProductoDTO> productosDTO) {

        List<Producto> productos = new LinkedList<>();

        for (ProductoDTO productoDTO : productosDTO) {
            Producto producto = new Producto();
            producto.setCantidad(productoDTO.getCantidad());
            producto.setNombre(productoDTO.getNombre());
            producto.setMarca(productoDTO.getMarca());
            producto.setCodigo(productoDTO.getCodigo());
            producto.setCosto(productoDTO.getCosto());
            productos.add(producto);
        }
        return productos;
    }

    /**
     * Permite convertir una lista de Proveedores DTO a Proveedores POJO
     *
     * @param proveedoresDTO La lista de Proveedor DTO
     * @return La lista de Proveedor POJO
     */
    private List<Proveedor> proveedoresDTOAProveedoresPOJO(List<ProveedorDTO> proveedoresDTO) {

        List<Proveedor> proveedores = new LinkedList<>();

        for (ProveedorDTO proveedorDTO : proveedoresDTO) {

            Direccion direccion = new Direccion();
            direccion.setCalle(proveedorDTO.getDireccion().getCalle());
            direccion.setCiudad(proveedorDTO.getDireccion().getCiudad());
            direccion.setCodigo_postal(proveedorDTO.getDireccion().getCodigo_postal());
            direccion.setColonia(proveedorDTO.getDireccion().getColonia());
            direccion.setNumero(proveedorDTO.getDireccion().getNumero());

            Proveedor proveedor = new Proveedor();

            proveedor.setNombre(proveedorDTO.getNombre());
            proveedor.setRfc(proveedorDTO.getRfc());
            proveedor.setTelefonos(proveedorDTO.getTelefonos());
            proveedor.setDireccion(direccion);

        }
        return proveedores;
    }

    /**
     * Método el cual encuentra una venta a partir del código
     *
     * @param codigo código de venta
     * @return venta que se encuentra
     */
    private Compra encontrarCompra(String codigo) {
        Compra compra = comprasDAO.encontrarCompra(codigo);
        return compra;
    }
}

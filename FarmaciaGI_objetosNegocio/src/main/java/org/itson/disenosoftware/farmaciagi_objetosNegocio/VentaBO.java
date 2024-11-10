package org.itson.disenosoftware.farmaciagi_objetosNegocio;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.IVentasDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.daos.VentasDAO;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Promocion;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.PromocionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.VentaDTO;
import org.itson.disenosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class VentaBO implements IVentaBO{

    private IVentasDAO ventasDAO;

    /**
     * Constructor.
     */
    public VentaBO() {
        IConexion conexion = new Conexion();
        ventasDAO = new VentasDAO(conexion);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void registrarVenta(VentaDTO ventaNueva) throws ObjetosNegocioException {
        Venta venta = null;
        String codigo;
        do {
            codigo = generarCodigoVenta();
            venta = encontrarVenta(codigo);
        } while (venta != null);

        venta = new Venta();
        venta.setCodigoVenta(codigo);
        venta.setCostoTotal(ventaNueva.getTotal());
        long millis = ventaNueva.getFecha().getTimeInMillis();
        venta.setFechaVenta(Instant.ofEpochMilli(millis));
        venta.setProductos(productosDTOAProductosPOJO(ventaNueva.getProductos()));
        venta.setPromociones(promocionesDTOAPromocionesPOJO(ventaNueva.getPromociones()));

        try {
            ventasDAO.registrarVenta(venta);
        } catch (PersistenciaException ex) {
            throw new ObjetosNegocioException("No se pudo registrar la venta.");
        }
    }

    /**
     * Permite generar un código para una venta.
     *
     * @return código generado
     */
    private String generarCodigoVenta() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int numeroAleatorio = random.nextInt(10);
            codigo.append(numeroAleatorio);
        }
        return codigo.toString();
    }

    /**
     * Método el cual encuentra una venta a partir del código
     *
     * @param codigo código de venta
     * @return venta que se encuentra
     */
    private Venta encontrarVenta(String codigo) {
        Venta venta = ventasDAO.encontrarVenta(codigo);
        return venta;
    }

    /**
     * Permite convertir una lista de Producto DTO a Producto POJO
     *
     * @param productosDTO La lista de Producto DTO
     * @return La lista de Producto POJO
     */
    List<Producto> productosDTOAProductosPOJO(List<ProductoDTO> productosDTO) {

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
     * Permite convertir una lista de Promocion DTO a Promocion POJO
     *
     * @param productosDTO La lista de Promocion DTO
     * @return La lista de Promocion POJO
     */
    private List<Promocion> promocionesDTOAPromocionesPOJO(List<PromocionDTO> promocionesDTO) {
        List<Promocion> promociones = new LinkedList<>();

        for (PromocionDTO promocionDTO : promocionesDTO) {
            Promocion promocion = new Promocion();
            promocion.setCantidad(promocionDTO.getCantidad());
            promocion.setCodigo(promocionDTO.getCodigo());
            promocion.setDescripcion(promocionDTO.getDescripcion());
            promocion.setPrecioUnitario(promocionDTO.getPrecioUnitario());
            promocion.setProducto(promocion.getProducto());

            promociones.add(promocion);
        }
        return promociones;
    }
    
    
    
   
  
}

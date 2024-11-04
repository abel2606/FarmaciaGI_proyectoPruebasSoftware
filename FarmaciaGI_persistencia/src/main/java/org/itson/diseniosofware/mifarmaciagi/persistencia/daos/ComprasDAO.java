/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Compra;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;

/**
 * Clase DAO para compras, esta clase esta encargada de la gestion y conexión
 * con la base de datos, todo referente a compras.
 * 
 * @author Enrique Rodriguez
 */
public class ComprasDAO implements IComprasDAO {

    private final MongoCollection<Compra> collection;
    private final MongoCollection<Proveedor> collection2;
    private IProductosDAO productosDAO;

    /**
     * Constructor que recibe la conexión al mecanismo de persistencia.
     *
     * @param conexion La conexión al mecanismo de persistencia
     */
    public ComprasDAO(IConexion conexion) {
        MongoDatabase baseDatos = conexion.crearConexion();
        this.productosDAO = new ProductosDAO(conexion);
        collection = baseDatos.getCollection("compras", Compra.class);
        collection2 = baseDatos.getCollection("proveedores", Proveedor.class);

    }

    /**
     * Método el cual nos permitirá registrar una compra de productos, a una bd
     *
     * @param compra compra a registrar
     * @return compra registrada
     * @throws PersistenciaException en caso de no poder registrarla
     */
    @Override
    public Compra registrarCompra(Compra compra) throws PersistenciaException {
        //añadir método para validar que no exista la compra
        if (encontrarCompra(compra.getCodigo()) == null) {
            collection.insertOne(compra);
            return compra;
        } else {
            throw new PersistenciaException("ERROR: Esta compra ya esta registrada");
        }

    }

    /**
     * Este método se encarga de encontrar ua venta en la bd
     *
     * @param codigo código de la venta que se desea encontrar
     * @return retorna la venta que se encontro
     */
    @Override
    public Compra encontrarCompra(String codigo) {

        Compra compraEncontrada;

        compraEncontrada = collection.find(eq("codigo", codigo)).first();

        if (compraEncontrada == null) {
            return null;
        }
        return compraEncontrada;

    }

    /**
     * Método para encontrar a los proveedores que le pertenecen a la compra de
     * un producto.
     *
     * @param producto Producto que referencia proveedores.
     * @return lista de proveedores
     */
    @Override
    public List<Proveedor> encontrarProveedores(Producto producto) {
        producto = productosDAO.obtenerProducto(producto);
        List<Proveedor> proveedores = new ArrayList<>();
        LinkedList<ObjectId> proveedoresIds = producto.getId_proveedores();
        for (ObjectId proveedorId : proveedoresIds) {
            Proveedor proveedor = collection2.find(Filters.eq("_id", proveedorId)).first();
            if (proveedor != null) {
                proveedores.add(proveedor);

            }
        }
        return proveedores;

    }
}

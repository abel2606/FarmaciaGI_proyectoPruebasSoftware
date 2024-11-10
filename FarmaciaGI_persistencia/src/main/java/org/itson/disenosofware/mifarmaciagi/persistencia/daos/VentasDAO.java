package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Venta;

public class VentasDAO implements IVentasDAO {

    private MongoCollection<Venta> collection;

    /**
     * Constructor que recibe la conexión al mecanismo de persistencia.
     *
     * @param conexion La conexión al mecanismo de persistencia
     */
    public VentasDAO(IConexion conexion) {
        MongoDatabase baseDatos = conexion.crearConexion();

        collection = baseDatos.getCollection("ventas", Venta.class);
    }

    /**
     * Método el cual nos permitirá registrar una venta a la bd
     *
     * @param venta venta a registrar
     * @return regresa la venta en caso de éxito
     * @throws PersistenciaException en caso de no poder registrarla
     */
    @Override
    public Venta registrarVenta(Venta venta) throws PersistenciaException {
        if (encontrarVenta(venta.getCodigoVenta()) == null) {
            collection.insertOne(venta);

            return venta;
        } else {
            throw new PersistenciaException("ERROR: Esta venta ya esta registrada");
        }
    }

    /**
     * Este método se encarga de encontrar ua venta en la bd
     *
     * @param codigo código de la venta que se desea encontrar
     * @return retorna la venta que se encontro
     */
    @Override
    public Venta encontrarVenta(String codigo) {

        Venta ventaEncontrada;

        ventaEncontrada = collection.find(eq("codigoVenta", codigo)).first();

        if (ventaEncontrada == null) {
            return null;
        }
        return ventaEncontrada;

    }

}

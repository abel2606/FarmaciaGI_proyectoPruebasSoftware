package org.itson.disenosofware.mifarmaciagi.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.itson.disenosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.disenosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.disenosofware.mifarmaciagi.persistencia.entidades.Promocion;

public class PromocionesDAO implements IPromocionesDAO {

    private final MongoDatabase baseDatos;
    private String nombreColeccion;

    /**
     * Constructor que recibe la conexión al mecanismo de persistencia.
     *
     * @param conexion La conexión al mecanismo de persistencia
     */
    public PromocionesDAO(IConexion conexion) {
        baseDatos = conexion.crearConexion();
        nombreColeccion = "promociones";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Promocion obtenerPromocion(Promocion promocionBuscada) {
        MongoCollection<Promocion> coleccion = baseDatos.getCollection(nombreColeccion, Promocion.class);

        Promocion promocionRegistro = coleccion.find(eq("codigo", promocionBuscada.getCodigo())).first();

        return promocionRegistro;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void registrarPromocion(Promocion promocionNueva) throws PersistenciaException {
        if (obtenerPromocion(promocionNueva) == null) {
            MongoCollection<Promocion> coleccion = baseDatos.getCollection(nombreColeccion, Promocion.class);

            coleccion.insertOne(promocionNueva);
        } else {
            throw new PersistenciaException("La promoción ya existe.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void actualizarPromocion(Promocion promocionActualizada) throws PersistenciaException {
        if (obtenerPromocion(promocionActualizada) != null) {
            MongoCollection<Promocion> coleccion = baseDatos.getCollection(nombreColeccion, Promocion.class);
            
            coleccion.updateOne(new Document()
                    .append("codigo", promocionActualizada.getCodigo()),
                    new Document("$set", new Document()
                            .append("descripcion", promocionActualizada.getDescripcion())
                            .append("producto", promocionActualizada.getProducto())
                            .append("cantidad", promocionActualizada.getCantidad())
                            .append("precioUnitario", promocionActualizada.getPrecioUnitario())));
        } else {
            throw new PersistenciaException("La promoción no existe.");
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void eliminarPromocion(Promocion promocionEliminar) throws PersistenciaException {
        if (obtenerPromocion(promocionEliminar) != null) {
            MongoCollection coleccion = baseDatos.getCollection(nombreColeccion, Promocion.class);
            
            coleccion.deleteOne(new Document().append("codigo", promocionEliminar.getCodigo()));
        } else {
            throw new PersistenciaException("La promoción no existe.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Promocion> obtenerPromociones() {
        MongoCollection<Promocion> coleccion = baseDatos.getCollection(nombreColeccion, Promocion.class);

        List<Promocion> promociones = new LinkedList<>();
        coleccion.find().into(promociones);

        return promociones;
    }

}

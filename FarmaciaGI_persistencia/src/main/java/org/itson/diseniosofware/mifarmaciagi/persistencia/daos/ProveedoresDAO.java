package org.itson.diseniosofware.mifarmaciagi.persistencia.daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;

public class ProveedoresDAO implements IProveedoresDAO {

    private static final Logger logger = Logger.getLogger(ProveedoresDAO.class.getName());
    private MongoCollection<Proveedor> collection;

    /**
     * Constructor de la clase ProveedoresDAO la cual realiza la conexion con la BD,
     * y selecciona donde trabajaremos.
     * @param conexion 
     */
    public ProveedoresDAO(IConexion conexion) {
        MongoDatabase baseDatos = conexion.crearConexion();
        collection = baseDatos.getCollection("proveedores", Proveedor.class);
    }

    /**
     * Registra un nuevo proveedor.
     * @param proveedorNuevo proveedor nuevo a registrar.
     * @return El proveedor registrado.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public Proveedor registrarProveedor(Proveedor proveedorNuevo) throws PersistenciaException {
        // Verificamos existencia en el inventario
        if (obtenerProveedor(proveedorNuevo) == null) {
            collection.insertOne(proveedorNuevo);
            return proveedorNuevo;
        } else {
            throw new PersistenciaException("Error: El proveedor ya esta registrado");
        }
    }

    /**
     * Elimina un proveedor existente.
     * @param proveedor proveedor a eliminar de la lista de provedores.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public void eliminarProveedor(Proveedor proveedor) throws PersistenciaException {
        proveedor = obtenerProveedor(proveedor);
        if (proveedor != null) {
            Bson filtro = eq("rfc", proveedor.getRfc());
            collection.deleteOne(filtro);
            logger.log(Level.INFO, "Proveedor eliminado");
        } else {
            throw new PersistenciaException("Error: No existe un proveedor con esos datos");
        }
    }

    /**
     * Actualiza los datos de un proveedor existente.
     * @return El proveedor actualizado.
     * @param proveedorActualizar proveedor a actualizar sus datos.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public Proveedor actualizarProveedor(Proveedor proveedorActualizar) throws PersistenciaException {
        Proveedor proveedorDeseado = obtenerProveedor(proveedorActualizar);
        
        if (proveedorDeseado != null) {
            Proveedor provedorActualizado = collection.findOneAndUpdate(
                    new Document("_id", proveedorDeseado.getId()),
                    new Document("$set", new Document()
                            .append("nombre", proveedorActualizar.getNombre())
                            .append("telefonos", proveedorActualizar.getTelefonos())
                            .append("direccion", proveedorActualizar.getDireccion())
                    )
            );
            logger.log(Level.INFO, "Producto Actualizado");
            return provedorActualizado; // TODO: Checar si esta bien ya que regresa el anterior
        } else {
            throw new PersistenciaException("Error: No existe el proveedor");
        } 
    }

    /**
     * Obtiene un proveedor.
     * @param proveedor proovedor a buscar.
     * @return El proveedor obtenido.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public Proveedor obtenerProveedor(Proveedor proveedor) throws PersistenciaException {
        Proveedor proveedorEncontrado;
        
        proveedorEncontrado = collection.find(eq("rfc",proveedor.getRfc())).first();
        
        if (proveedorEncontrado == null) {
            return null;
        }
        
        return proveedorEncontrado;
    }

    /**
     * Obtiene una lista de todos los proveedores registrados.
     * @return Una lista de proveedores.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public List<Proveedor> obtenerProveedores() throws PersistenciaException {
        List<Proveedor> proveedores = new LinkedList<>();
        
        collection.find().into(proveedores);
        
        return proveedores;
    }
    
}

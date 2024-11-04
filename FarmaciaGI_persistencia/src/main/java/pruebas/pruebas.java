package pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.Conexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Conexion.IConexion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.Exception.PersistenciaException;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.IProveedoresDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ProductosDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.daos.ProveedoresDAO;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Direccion;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Producto;
import org.itson.diseniosofware.mifarmaciagi.persistencia.entidades.Proveedor;

public class pruebas {

    public static void main(String[] args) {
        IConexion conexion = new Conexion();
        IProveedoresDAO provColeccion = new ProveedoresDAO(conexion);
        IProductosDAO productosDAO = new ProductosDAO(conexion);
        
        List<String> telefonos = new ArrayList<>();
        telefonos.add("6442522645");
        telefonos.add("6442522646");
        
        Proveedor proveedor = new Proveedor(
                "Similares", 
                new Direccion("Sonora", "Centro", "5012", "85140", "Cd.Obregón"), 
                telefonos, 
                "RFC4405010"
        );
        
        try {
            provColeccion.registrarProveedor(proveedor);
        } catch (PersistenciaException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Producto producto = new Producto();
        
//        producto.setCodigo("AAA-000");
        
//        try {
//            productosDAO.asignarProveedor(producto, proveedor);
////        Proveedor proveedor = new Proveedor();
////        proveedor.setNombre("Amazon");
//        } catch (PersistenciaException ex) {
//            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            System.out.println(provColeccion.obtenerProveedores());
//        } catch (PersistenciaException ex) {
//            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}

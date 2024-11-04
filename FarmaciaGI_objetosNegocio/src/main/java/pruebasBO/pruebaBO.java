package pruebasBO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.disenosoftware.farmaciagi_dtos.DireccionDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProductoDTO;
import org.itson.disenosoftware.farmaciagi_dtos.ProveedorDTO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.ProductoBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.ProveedorBO;
import org.itson.diseñosoftware.farmaciagi_objetosNegocio.excepciones.ObjetosNegocioException;

public class pruebaBO {

    public static void main(String[] args) {
        ProveedorBO proveedorBO = new ProveedorBO();

        List<String> telefonos = new ArrayList<>();
        telefonos.add("644252264259");
        telefonos.add("644252264260");

        ProveedorDTO proveedorNuevoDTO = new ProveedorDTO(
                "TUFESA",
                new DireccionDTO(
                        "Blvd Casanova",
                        "Casa Blanca",
                        "451",
                        "85900",
                        "Cd.Obregon"
                ),
                telefonos,
                "RFC4405010"
        );
        
        ProductoDTO producto = new ProductoDTO();
        producto.setCodigo("SLD-541");
        
        ProductoBO productoBO = new ProductoBO();
        
        try {
            productoBO.asignarProveedor(producto, proveedorNuevoDTO);
        } catch (ObjetosNegocioException ex) {
            Logger.getLogger(pruebaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        

//        ProveedorDTO proveedorNuevoDTO = new ProveedorDTO();
//        proveedorNuevoDTO.setRfc("JGLOIUJ090");
                
//        proveedorBO.actualizarProveedor(proveedorNuevoDTO);
    }
}

package org.itson.disenosoftware.farmaciagi_dtos;

import java.util.List;

public class ProveedorDTO {
    private String nombre;
    private DireccionDTO direccion;
    private List<String> telefonos;
    private String rfc;

   /**
     * Constructor por defecto de la clase ProveedorDTO.
     * Crea una instancia de ProveedorDTO con valores por defecto para sus atributos.
     */
    public ProveedorDTO() {
    }

    /**
     * Constructor de la clase ProveedorDTO.
     * Crea una instancia de ProveedorDTO con los valores especificados.
     * 
     * @param nombre El nombre del proveedor
     * @param direccion La dirección del proveedor
     * @param telefonos La lista de teléfonos del proveedor
     * @param rfc El RFC del proveedor
     */
    public ProveedorDTO(String nombre, DireccionDTO direccion, List<String> telefonos, String rfc) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.rfc = rfc;
    }

    /**
     * Obtiene el nombre del proveedor.
     * 
     * @return El nombre del proveedor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del proveedor.
     * 
     * @param nombre El nombre del proveedor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del proveedor.
     * 
     * @return La dirección del proveedor
     */
    public DireccionDTO getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del proveedor.
     * 
     * @param direccion La dirección del proveedor
     */
    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la lista de teléfonos del proveedor.
     * 
     * @return La lista de teléfonos del proveedor
     */
    public List<String> getTelefonos() {
        return telefonos;
    }

    /**
     * Establece la lista de teléfonos del proveedor.
     * 
     * @param telefonos La lista de teléfonos del proveedor
     */
    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

     /**
     * Obtiene el RFC del proveedor.
     * 
     * @return El RFC del proveedor
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Establece el RFC del proveedor.
     * 
     * @param rfc El RFC del proveedor
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Retorna una representación en cadena de texto del objeto ProveedorDTO.
     * 
     * @return Una cadena que representa al objeto ProveedorDTO
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProveedorDTO{");
        sb.append("nombre=").append(nombre);
        sb.append(", direccion=").append(direccion);
        sb.append(", telefonos=").append(telefonos);
        sb.append(", rfc=").append(rfc);
        sb.append('}');
        return sb.toString();
    }    
}

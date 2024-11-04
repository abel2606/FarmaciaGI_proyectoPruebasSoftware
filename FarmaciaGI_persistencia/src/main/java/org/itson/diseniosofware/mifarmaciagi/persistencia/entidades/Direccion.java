package org.itson.diseniosofware.mifarmaciagi.persistencia.entidades;

public class Direccion {
    private String calle;
    private String colonia;
    private String numero;
    private String codigo_postal;
    private String ciudad;

    /**
     * Constructor por defecto de la clase Direccion.
     */
    public Direccion() {
    }

    /**
     * Constructor con parámetros de la clase Direccion.
     * @param calle La calle de la dirección.
     * @param colonia La colonia de la dirección.
     * @param numero El número de la dirección.
     * @param codigo_postal El código postal de la dirección.
     * @param ciudad La ciudad de la dirección.
     */
    public Direccion(String calle, String colonia, String numero, String codigo_postal, String ciudad) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la calle de la dirección.
     * @return La calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección.
     * @param calle La calle de la dirección a establecer.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene la colonia de la dirección.
     * @return La colonia de la dirección.
     */
    public String getColonia() {
        return colonia;
    }

    /** 
     * Establece la colonia de la dirección.
     * @param colonia La colonia de la dirección a establecer.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene el número de la dirección.
     * @return El número de la dirección.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la dirección.
     * @param numero El número de la dirección a establecer.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el código postal de la dirección.
     * @return El código postal de la dirección.
     */
    public String getCodigo_postal() {
        return codigo_postal;
    }

    /**
     * Establece el código postal de la dirección.
     * @param codigo_postal El código postal de la dirección a establecer.
     */
    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    /**
     * Obtiene la ciudad de la dirección.
     * @return La ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad de la dirección.
     * @param ciudad La ciudad de la dirección a establecer.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres de la instancia Direccion.
     * @return Una cadena que representa la instancia Direccion.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Direccion{");
        sb.append(", calle=").append(calle);
        sb.append(", colonia=").append(colonia);
        sb.append(", numero=").append(numero);
        sb.append(", codigo_postal=").append(codigo_postal);
        sb.append(", ciudad=").append(ciudad);
        sb.append('}');
        return sb.toString();
    }

}

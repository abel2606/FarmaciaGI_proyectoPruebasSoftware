package org.itson.diseniosofware.mifarmaciagi.persistencia.entidades;

import java.util.LinkedList;
import org.bson.types.ObjectId;

/**
 *
 * @author Hector Espinoza
 */
public class Producto {

    private ObjectId _id; //id del producto
    private String nombre; //nombre delñ producto
    private String marca; //marca del producto
    private Float costo; //costo del producto
    private String codigo; //codigo del producto
    private Integer cantidad; //cantidad del producto
    private LinkedList <ObjectId> id_proveedores; //proveedores que suministtran el producto

    
    
    /**
     * Constructor default de un objeto tipo producto
     */
    public Producto() {
    }
    
    /**
     * Constructor que recibe el nombre, el costo y el código del producto.
     *
     * @param nombre El nombre del producto
     * @param costo El costo del producto
     * @param codigo El código del producto
     */
    public Producto(String nombre, Float costo, String codigo) {
        this.nombre = nombre;
        this.costo = costo;
        this.codigo = codigo;
    }

    /**
     * Constructor que recibe el nombre, la marca, el costo, el código y la
     * cantidad del producto.
     *
     * @param nombre El nombre del producto
     * @param marca La marca del producto
     * @param costo El costo del producto
     * @param codigo El código del producto
     * @param cantidad La cantidad del producto
     */
    public Producto(String nombre, String marca, Float costo, String codigo, Integer cantidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    /**
     * Retorna el id del producto
     *
     * @return id del producto
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     * Establece el id del producto
     *
     * @param _id id del producto
     */
    public void setId(ObjectId _id) {
        this._id = _id;
    }

    /**
     * Retorna el el nombre del producto
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establec el nombre del producto
     *
     * @param nombre nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la marca del producto
     *
     * @return marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del producto
     *
     * @param marca marca del producto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna el costo del producto
     *
     * @return coste del producto
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Establece el costo del producto
     *
     * @param costo coste del producto
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Retorna el codigo del producto
     *
     * @return codigo del producto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de producto
     *
     * @param codigo código del producto
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna la cantidad del producto
     *
     * @return cantidad del producto
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto
     *
     * @param cantidad cantidad del producto
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Obtiene los proveedores del producto
     * @return lista de los proveedores
     */
    public LinkedList<ObjectId> getId_proveedores() {
        return id_proveedores;
    }

    /**
     * Establece los proveedores del producto
     * @param id_proveedores lista de proveedores
     */
    public void setId_proveedores(LinkedList<ObjectId> id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    /**
     * Permite obtener una cadena de texto con los atributos del producto.
     *
     * @return Una cadena de texto con los atributos del producto
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("_id=").append(_id);
        sb.append(", nombre=").append(nombre);
        sb.append(", marca=").append(marca);
        sb.append(", costo=").append(costo);
        sb.append(", codigo=").append(codigo);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", id_proveedores=").append(id_proveedores);
        sb.append('}');
        return sb.toString();
    }
    

}

package org.itson.disenosoftware.farmaciagi_subsistema_proveedores.excepciones;

public class GestorProveedoresException extends Exception{
    
    /**
     * Constructor que recibe el mensaje de la excepción.
     *
     * @param message El mensaje de la excepción
     */
    public GestorProveedoresException(String message) {
        super(message);
    }
}

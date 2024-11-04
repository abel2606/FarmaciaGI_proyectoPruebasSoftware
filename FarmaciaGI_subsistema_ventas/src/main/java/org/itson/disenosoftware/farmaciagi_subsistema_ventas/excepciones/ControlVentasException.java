package org.itson.disenosoftware.farmaciagi_subsistema_ventas.excepciones;

public class ControlVentasException extends Exception{

    /**
     * Constructor que recibe el mensaje de la excepción.
     *
     * @param message El mensaje de la excepción
     */
    public ControlVentasException(String message) {
        super(message);
    }
    
}

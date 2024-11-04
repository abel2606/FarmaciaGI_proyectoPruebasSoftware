package org.itson.disenosoftware.farmaciagi_subsistema_productos.Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hector Espinoza
 */
public class ExpresionesRegulares {
    
    public static boolean validarCodigo(String codigo) {
        String regex = "^[A-Z]{3}-\\d{3}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);
        
        return matcher.matches();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcufinal;

/**
 * Clase que arroja el error cuando la pila esta vacía.
 * @author danieltena
 */
public class ExcepcionColeccionVacia extends RuntimeException{
    
    public ExcepcionColeccionVacia(String message) {
        super(message);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcufinal;

/**
 * Clase para probar el funcionamiento del codigo de clase Calculadora
 * @author danieltena
 */
public class Pruebas <T>{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PilaA infija = new PilaA(40);
        
        infija.push(1.0);
        infija.push('+');
        infija.push(2.0);
        
        Calculadora calc = new Calculadora();
        
        PilaA fija = calc.convPostfija(infija);
        
        System.out.println(fija.toString());
        
        System.out.println("\nResultado = " + calc.calculaPostFija(fija));
        
        
    }
    
}
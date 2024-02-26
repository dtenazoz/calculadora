/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package calcufinal;

import junit.framework.TestCase;
/**
 * JUnit Test de la calculadora.
 * @author mariariverarubio
 */
public class CalculadoraTest extends TestCase{
    private Calculadora calculadora;
    public void escenario(){
        calculadora = new Calculadora();
    }
    
  
    public void convPostfija() {
    
        escenario();
        PilaA postfija=new PilaA(90);
        postfija.push(5);
        postfija.push(5);
        postfija.push(5);
        postfija.push('-');
        postfija.push('*');
        
        PilaA infija=new PilaA(90);
        infija.push(5);
        infija.push('*');
        infija.push('(');
        infija.push(5);
        infija.push('-');
        infija.push(3);
        infija.push(')');
        
        assertEquals(postfija,calculadora.convPostfija(infija));
    }
    
    public void calculaPostFija() {
        escenario();
        PilaA postfija=new PilaA(90);
        postfija.push(5);
        postfija.push(5);
        postfija.push(5);
        postfija.push('-');
        postfija.push('*');
        
        assertEquals(10,calculadora.calculaPostFija(postfija));
    }

}
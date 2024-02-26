/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcufinal;

/**
 * Clase en donde se programa la pila genérica que se utiliza en el programa.
 * @author danieltena
 */
public class PilaA <T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private int MAX=100;
    
    /** 
     * Constructor de la clase PilaA.
     * Crea pila vacía con límite de 100 elementos
     */
   public PilaA (){
       pila = (T[]) new Object[MAX];
       tope= -1;
   }
   
   /**
    * Contructor de la clase PilaA.
    * Crea una pila vacía con límite de elementos que el usuario escoja
    * @param max 
    */
    public PilaA(int max) {
     
        this.pila = (T[]) new Object[max];
        this.tope = -1;
    }
    
    /**
    * Inserta elemento (dato) a la pila en su posición superior.
    * @param dato Elemento a insertar
    */
    @Override
    public void push(T dato) {
        if(tope == pila.length - 1)
            expande();
        tope++;
        pila[tope] = dato;
    }
    
    /**
     * Crece el tamaño de la pila en la que se inserta el dato
     */
    private void expande() {
        T[] exp = (T[]) new Object[pila.length + 1];
        for(int i = 0; i < pila.length; i++) {
            exp[i] = pila[i];
        }
        pila = exp;
    }

    /**
     * Elimina el dato que se encuentra en la parte superior de la pila.
     * @return El dato eliminado
     */
    @Override
    public T pop() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("La pila está vacía");// lanza la excepcion
        T eliminado;
        eliminado = pila[tope];
        pila[tope] = null;
        tope--;
        return eliminado;
    }

    /**
     * Revisa si la pila esta vacía, es decir, que no tenga datos.
     * @return El valor del tope, la posición del último dato, si esta vacía regresa -1
     */
    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    /**
     * Revisa el dato que se encuentra en el tope de la pila.
     * @return El dato del tope de la pila
     */
    @Override
    public T peek() {
        if(this.isEmpty())
            throw new ExcepcionColeccionVacia("La pila esta vacia");
        return pila[tope];
    }
    
    /**
     * Imprime en una cadena los datos de la pila.
     * @return La cadena de los datos de la pila
     */
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("Pila de 0 a Tope\n");
        
        for(int i = 0; i <= tope; i++)
            sB.append(pila[i]).append(" ");
        
        return sB.toString();
    }
    
    /**
     * Método que invierte el orden de la pila.
     * @return La pila invertida
     */
    public PilaA voltea() {
        T[] volteado = (T[]) new Object[this.tope + 1];
        int i = 0;
        
        while(!this.isEmpty()) {
            volteado[i] = this.pop();
            i++;
        }
        
        for(i = 0; i < volteado.length; i++) {
            this.push(volteado[i]);
        }
        
        return this;
    }

    /**
     * Método que regresa la pila.
     * @return La pila
     */
    public T[] getPila() {
        return pila;
    }

    /**
     * Método que regresa el tope de la pila.
     * @return El tope de la pila
     */
    public int getTope() {
        return tope;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcufinal;

/**
 * Esta clase representa una calculadora que puede convertir una expresi�n infija en una expresi�n postfija y calcular el resultado de la expresi�n postfija.
 * 
 * @param <T> Tipo de datos que maneja la calculadora.
 */
public class Calculadora <T>{

    private final int MAXPOSTF = 25;
    private PilaA pilaControl;
    private final int MAXCONTROL = 30;
    
    /**
     * Constructor por defecto que inicializa la pila de control.
     */
    public Calculadora() {
        this.pilaControl = new PilaA(MAXCONTROL);
    }
    
    /**
     * M�todo que convierte una expresi�n infija en una expresi�n postfija.
     * 
     * @param infija Pila que contiene la expresi�n infija.
     * @return Pila que contiene la expresi�n postfija.
     */
    public PilaA convPostfija(PilaA infija) {
    PilaA postfija = new PilaA(MAXPOSTF);

    infija = infija.voltea(); 

    while (!infija.isEmpty()) {
        if (infija.peek() instanceof Double) {
            postfija.push(infija.pop());
        } else {
            char currentChar = (char) infija.pop();

            if (currentChar == ')') {
                while (!pilaControl.isEmpty() && (char) pilaControl.peek() != '(') {
                    postfija.push(pilaControl.pop());
                }
                if (!pilaControl.isEmpty()) {
                    pilaControl.pop(); 
                }
            } else if (currentChar == '(') {
                pilaControl.push(currentChar);
            } else if (currentChar == '-') {
            if (!infija.isEmpty() && ((char) infija.peek() == '(' || Character.isDigit((char) infija.peek()))) {
            
                while (!pilaControl.isEmpty() && checkJerarquia((char) pilaControl.peek())) {
                 postfija.push(pilaControl.pop());
            }   
                pilaControl.push(currentChar);
            
                StringBuilder decimalNumber = new StringBuilder("-");
                while (!infija.isEmpty() && (Character.isDigit((char) infija.peek()) || (char) infija.peek() == '.')) {
                 decimalNumber.append(infija.pop());
            }
                postfija.push(Double.parseDouble(decimalNumber.toString()));
            } else {
        
            pilaControl.push(currentChar);
             }
        }
            else if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder decimalNumber = new StringBuilder();
                decimalNumber.append(currentChar);

                while (!infija.isEmpty() &&
                        (Character.isDigit((char) infija.peek()) || (char) infija.peek() == '.')) {
                    decimalNumber.append(infija.pop());
                }

                postfija.push(Double.parseDouble(decimalNumber.toString()));
            } else {
                while (!pilaControl.isEmpty() && checkJerarquia((char) pilaControl.peek())) {
                    postfija.push(pilaControl.pop());
                }
                pilaControl.push(currentChar);
            }
        }
    }

    while (!pilaControl.isEmpty()) {
        postfija.push(pilaControl.pop());
    }

    return postfija;
}
    /**
     * M�todo que verifica si un operador tiene mayor jerarqu�a que otro.
     * 
     * @param value Operador a comparar.
     * @return true si el operador tiene mayor jerarqu�a que el operador en la cima de la pila de control, false en caso contrario.
     */
    private boolean checkJerarquia(char value) {
    boolean res = false;
    int jerarquia, jerarquiaTop;

    jerarquia = switch (value) {
        case '(', ')' -> 0;
        case '+', '-' -> 1;
        case '*', '/' -> 2;
        case '^' -> 3;
        default -> 4;
    };


    jerarquiaTop = switch ((char) pilaControl.peek()) {
        case '(', ')' -> 0;
        case '+', '-' -> 1;
        case '*', '/' -> 2;
        case '^' -> 3;
        default -> 4;
    };

    if (jerarquia >= jerarquiaTop)
        res = true;

    return res;
}
    /**
     * M�todo que vac�a la pila de control.
     */
    private void vaciaPilaControl() {
        while(!pilaControl.isEmpty())
            pilaControl.pop();
    }
    
    /**
     * M�todo que calcula el resultado de una expresi�n postfija.
     * 
     * @param pilaPostfija Pila que contiene la expresi�n postfija.
     * @return Resultado de la expresi�n postfija.
     */
    public double calculaPostFija(PilaA pilaPostfija) {
    pilaPostfija = pilaPostfija.voltea();

    double res = 0;
    double a, b;

    while (!pilaPostfija.isEmpty()) {
        System.out.println("Pila: " + pilaPostfija.toString());  

        if (pilaPostfija.peek() instanceof Double) {
            
            pilaControl.push(pilaPostfija.pop());
        } else {
            char operador = (char) pilaPostfija.pop();

            if (esOperador(operador)) {
                
                if (pilaControl.isEmpty()) {
                    throw new ExcepcionColeccionVacia("Not enough operands for operator: " + operador);
                }

                
                switch (operador) {
                    case '+':
                        a = (double) pilaControl.pop();
                        b = (double) pilaControl.pop();
                        res = b + a;
                        break;
                    case '-':
                        a = (double) pilaControl.pop();
                        b = (double) pilaControl.pop();
                        res = b + a;
                        break;
                    case '*':
                        a = (double) pilaControl.pop();
                        b = (double) pilaControl.pop();
                        res = b * a;
                        break;
                    case '/':
                        a = (double) pilaControl.pop();
                        b = (double) pilaControl.pop();
                        if (a == 0.0) {
                            throw new ExcepcionColeccionVacia("Error div between zero");
                        }
                        res = b / a;
                        break;
                    case '^':
                        a = (double) pilaControl.pop();
                        b = (double) pilaControl.pop();
                        res = Math.pow(b, a);
                        break;
                }

                
                pilaControl.push(res);
            }
        }
    }

    if (pilaControl.isEmpty()) {
        throw new ExcepcionColeccionVacia("Invalid expression: Not a single result on the control stack");
    }

    return res;
}
    /**
     * Método que determina si el caracter es un operador o es un número.
     * @param c El caracter que se analiza
     * @return Verdadero o falso dependiendo si es operador o no
     */
    private boolean esOperador(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
}
    /**
     * M�todo que resta uno al n�mero pasado como argumento si es mayor que el l�mite.
     * 
     * @param num N�mero a restar.
     * @param lim L�mite a comparar.
     * @return N�mero restado si es mayor que el l�mite, el mismo n�mero en caso contrario.
     */
    private int restaNoMenNum(int num, int lim) {
        if (num > lim)
            num--;
        return num;
    }
}

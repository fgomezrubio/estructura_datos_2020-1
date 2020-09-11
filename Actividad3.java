/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad3;

import java.util.Stack;

/**
 *
 * @author fgomezrubio
 * Implementacion de los algoritmos descritos en el tema tres de la materia de Estructuras de Datos
 * 
 */
public class Actividad3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String infija = "7-(2*3)+3";
        String npostfija = new String();
        
        npostfija=getExpresionPostFija(infija);    
        System.out.print("npostfija: "+npostfija+" -> "+setValueExpresionPostFija(npostfija));
        
    }
    
    public static Double setValueExpresionPostFija(String npostfija){

        // 1. Crear una pila vacía de operandos.  
        Stack pilaOperandos = new Stack();   // 1. Crear una pila vacía de operandos.
        // 2. Declarar dos variables, operando1 y operando2.
        String operando2;
        String operando1;
        
        for (int i=0;i<npostfija.length();i++) //3. Ir recorriendo la expresión postfija y preguntar si es un operando
        {
             if (isOperando(npostfija.charAt(i)))                    
                        pilaOperandos.push(npostfija.charAt(i));     // a. En caso de que sí lo sea, meterlo a la pila
             else                                                    // b. // a. // a. En caso de que sí lo sea, meterlo a la pila
             {
                operando2 =  new StringBuilder().append(pilaOperandos.pop()).toString();  // i. Sacar el tope de la pila y asignarlo a la variable operando2.
                operando1=   new StringBuilder().append(pilaOperandos.pop()).toString();  // ii. Volver a sacar el tope de la pila y asignarlo a la variable operando1. Es importante hacerlo en ese orden.             
                String evaluator=new StringBuilder().append(operando1).append(npostfija.charAt(i)).append(operando2).toString();
                pilaOperandos.push(ExpressionCalculator.evaluate(evaluator));  // iii. Hacer la operación del operador y meter el resultado a la pila.  
             }
        }
         System.out.println(pilaOperandos.peek());  // 4. Una vez que se acabe de recorrer el resultado, la operación estará en el tope de la pila.
               
        return ((Double)pilaOperandos.peek());
    }
    public static String getExpresionPostFija(String infija){
        Stack pilaOperandos = new Stack();   // 1. Crear una pila vacía de operandos.
        String npostfija = new String();     // 2. Crear un string vacío, llamado npostfija.

        
        for (int i=0;i<infija.length();i++) //3. Recorrer el string de entrada con las siguientes condiciones:
        {
            if (isOperando(infija.charAt(i)))            // a. Si el elemento del string de entrada es un operando,
                npostfija = npostfija+infija.charAt(i);  // ponerlo en el string npostija.
            else                                         // b. Si el elemento es un operador
            {
                while(true){
                    if (pilaOperandos.empty())                    // i. Si la pila está vacía, ( este es el paso 3.2.1.)
                        pilaOperandos.push(infija.charAt(i));     //  meterlo en la pila.
                    else if (infija.charAt(i)!=')')               // ii. Si la pila no está vacía y no es un ).
                    {
                        // Revisar la siguiente tabla:
                        // ver getPesoExpresion & getPesoPila
                        if( getPesoExpresion(infija.charAt(i)) > getPesoPila((char)pilaOperandos.peek()) ) // Si el peso del operador en la expresión es mayor que el peso del operador en el tope de la pila
                            pilaOperandos.push(infija.charAt(i));                                      // En caso de ser así, meter el operador a la pila.
                        else                                                                           // En caso de que no sea así: 
                        {
                            npostfija = npostfija+(char) pilaOperandos.pop();                         // sacar el operador del tope de la pila y pasarlo al string npostfija y 
                            continue;                                                                 // volver al paso 3.2.1.
                        }
                    }
                    else  // iii. En caso de que sea un ),
                    {
                        while ((char)pilaOperandos.peek()!='(')      //  hasta encontrar un (        
                            npostfija = npostfija+(char) pilaOperandos.pop(); // sacar el operador del tope de la pila y pasarlo al string npostfija hasta encontrar un ( y sacarlo de la pila, 
                        
                        if ((char)pilaOperandos.peek()=='(') //el ( no va al string npostfija.
                            pilaOperandos.pop();
                    }
                    break;
                }
            }
        } // FGR. Terminamos de recorrer el string
        
        while (!(pilaOperandos.empty())) //4. Al final, vaciamos todos los operadores que quedaron en la fila y .
            npostfija = npostfija+(char) pilaOperandos.pop(); // los agregamos a la expresión postfija
    
        return npostfija;
    }
    // Revisar la siguiente tabla:
    public static int getPesoExpresion(char a){
        switch(a){
            case '^': return 4;
            case '*': return 2;
            case '/': return 2;
            case '+': return 1;
            case '-': return 1;
            case '(': return 5;
        }
        return -1;
    }
    
    public static int getPesoPila(char a){
        switch(a){
            case '^': return 3;
            case '*': return 2;
            case '/': return 2;
            case '+': return 1;
            case '-': return 1;
            case '(': return 0;
        }
        return -1;
    }
    
    public static boolean isOperando(char a){
        if (a>='0' && a<='9' )
            return true;
        return false;
    }
}

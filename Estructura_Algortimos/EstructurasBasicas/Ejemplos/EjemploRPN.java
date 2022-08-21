import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;
public class EjemploRPN 
{
    public static void main(String[] args) 
    {
        Stack<Double> pila = new Stack<>();
        while (StdIn.hasNextLine()) 
        {
            try 
            {
                String input = StdIn.readString();
                if (input.equals("+")) 
                {
                    double r = pila.pop()+pila.pop();
                    pila.push(r);
                }
                else if (input.equals("-"))
                {
                    double r = -pila.pop()+pila.pop();
                    pila.push(r);
                }
                else if (input.equals("abs"))
                {
                    double r= Math.abs(pila.pop());
                    pila.push(r);
                }
                else if (input.equals("sqrt"))
                {
                    double r= Math.sqrt(pila.pop());
                    pila.push(r);
                }
                else
                {
                    Double d = Double.parseDouble(input);
                    pila.push(d);
                }   
            } 
            catch (NoSuchElementException e) 
            {
            }
        }  
        StdOut.println(pila.pop());  
    }
}
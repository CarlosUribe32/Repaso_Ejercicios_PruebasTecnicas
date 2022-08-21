import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class PilaArreglo<Item> implements Iterable<Item>
{
    private Item[] pila;
    private int n;

    public PilaArreglo(int max) 
    {
        pila = (Item[])(new Object[max]);
    }

    public void push(Item s) throws Exception 
    {
        if(n>=pila.length) throw new Exception("Pila llena");
        pila[n++] = s;
    }
    public  Item pop() throws Exception
    {
        if (n<=0) throw new Exception("Pila Vacia");
        Item obj = pila[--n];
        pila[n] = null;
        return obj;
    }
    public boolean isEmpty() 
    {
        return n==0;
    }
    public int size() 
    {
        return n;
    }
    public Iterator<Item> iterator()
    {
        return new IteradorPila(); 
    }

    private class IteradorPila implements Iterator <Item>
    {
        private int pos = n;

        @Override
        public boolean hasNext() {
            return pos>0;
        }

        @Override
        public Item next() 
        {    
            return pila[--pos];
        }
        
    }

    public static void main(String[] args) throws Exception
    {
        PilaArreglo<String> pila = new PilaArreglo<>(5);
        pila.push("Hola");
        pila.push("12344");
        pila.push("Mundo");

        //Aca violamos la encapsulacion de la clase interna
        for (Iterator<String> pos = pila.iterator(); pos.hasNext();) 
        {
            StdOut.println(pos.next());
        }

        //Esto que sigue es el azucar sintactico del ciclo anterior
        for (String s : pila) 
        {
            StdOut.println(s);
        }
    }
}
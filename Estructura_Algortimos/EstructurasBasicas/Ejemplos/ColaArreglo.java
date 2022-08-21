import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class ColaArreglo <Item> implements Iterable<Item>
{
    private Item[] cola;
    private int n;

    public ColaArreglo(int max) 
    {
        cola = (Item[])(new Object[max]);
    }

    public void enqueue(Item s) throws Exception 
    {
        if(n>=cola.length) throw new Exception("Cola llena");
        cola[n++] = s;
    }
    public Item dequeue () throws Exception
    {
        if (n<=0) throw new Exception("Cola Vacia");
        Item obj = cola[0];
        for (int i = 1; i < n; i++) 
        {
            cola[i-1] = cola[i];
            if(i== n-1)
                cola[i] = null;
        }
        n--;
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
        return new IteradorCola(); 
    }
    private class IteradorCola implements Iterator <Item>
    {
        private int pos = 0;

        @Override
        public boolean hasNext() 
        {
            return pos<n;
        }

        @Override
        public Item next() 
        {
            return cola[pos++];
        }

    }

    public static void main(String[] args) throws Exception
    {
        ColaArreglo<String> cola = new ColaArreglo<>(5);
        cola.enqueue("Hola");
        cola.enqueue("Mundo");
        cola.enqueue("Como");
        cola.enqueue("Tas");
        cola.enqueue("Tu");

        for (String s : cola) 
        {
            StdOut.println(s);
        }

        cola.dequeue();
        for (String s : cola) 
        {
            StdOut.println(s);
        }
    }
}
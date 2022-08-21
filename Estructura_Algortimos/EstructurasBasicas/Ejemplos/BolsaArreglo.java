import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class BolsaArreglo <Item> implements Iterable<Item>
{
    private Item[] bolsa;
    private int n;

    public BolsaArreglo(int max) 
    {
        bolsa = (Item[])(new Object[max]);
    }

    public void add(Item i) throws Exception
    {
        if(n>=bolsa.length) throw new Exception("Pila llena");
        bolsa[n++] = i;
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
        return new IteradorBolsa();
    }
    private class IteradorBolsa implements Iterator <Item>
    {
        private int pos = n;

        @Override
        public boolean hasNext() {
           
            return pos>0;
        }
        @Override
        public Item next() 
        {
            return bolsa[--pos];
        } 
    }

    public static void main(String[] args) throws Exception
    {
        BolsaArreglo<String> bolsa = new BolsaArreglo<>(5);
        
        bolsa.add("Hola Gente");
        bolsa.add("Como estan");
        bolsa.add("Cuenten como van");

        for (String s : bolsa) 
        {
            StdOut.println(s);
        }
    }
}
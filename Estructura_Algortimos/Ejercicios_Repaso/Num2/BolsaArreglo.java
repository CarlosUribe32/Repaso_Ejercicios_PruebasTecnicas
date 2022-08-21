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
    public void remove(Item i)
    {
        for (int j = 0; j < n; j++) {
            if(bolsa[j]==i)
            {
                for (int j2 = j; j2 < n-1; j2++) {
                    bolsa[j2] = bolsa[j2+1];
                }
                n--;
                break;
            }
        }
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
        bolsa.add("Youte");

        bolsa.remove("Youte");

        for (String s : bolsa) 
        {
            StdOut.println(s);
        }
    }
}
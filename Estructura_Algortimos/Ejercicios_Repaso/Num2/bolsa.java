import java.util.*;
import edu.princeton.cs.algs4.*;
public class bolsa <Item> implements Iterable<Item>
{
    private class Nodo {
        Item item;
        Nodo sig;
    }
    private Nodo first;
    private int n;

    public void add(Item item) {
        Nodo x = new Nodo();
        x.item = item;
        x.sig = first;
        first = x;
        n++;
    }
    
    public void remove(Item i)
    {
        Nodo anterior = null;
        for(Nodo nodo = first; nodo!=null; nodo=nodo.sig)
        {
            if(i == nodo.item)
            {
                if (anterior == null)
                    first = first.sig;
                else
                    anterior.sig = nodo.sig;
                n--;
            }
            else
                anterior = nodo;
        }
    }
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() 
    {
        return new IteradorBolsa();
    }
    private class IteradorBolsa implements Iterator<Item>
    {
        private Nodo pos = first;

        @Override
        public boolean hasNext() {
            return pos!=null;
        }

        @Override
        public Item next() {
            Item item = pos.item;
            pos = pos.sig;
            return item;
        }
        
    }
    public static void main(String[] args) 
    {
        bolsa<Integer> b = new bolsa<>();
        b.add(1);
        b.add(23);
        b.add(45);
        b.add(56);
        b.add(78);

        for (Integer i : b) 
        {
            StdOut.println(i);
        }
        StdOut.println("");
        b.remove(23);
        for (Integer i : b) 
        {
            StdOut.println(i);
        }
    }
    
}
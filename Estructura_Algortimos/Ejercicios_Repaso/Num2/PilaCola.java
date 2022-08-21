import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class PilaCola<Item> implements Iterable<Item> 
{
    private class Nodo {
        Item item;
        Nodo sig;
        Nodo ant;
    }

    private Nodo first;
    private Nodo last;
    private int n;

    public void PushEnqueue(Item i)
    {
        Nodo x = new Nodo();
        x.item = i;
        if(last==null)
        {
            x.sig = first;
            first = x;
            last = x;
        }
        else
        {
            first.ant=x;
            x.sig = first;
            first = x;
        }
        n++;
    }
    public Item pop() throws Exception 
    {
        if (first == null)
            throw new Exception("Pila vacia");
        Item i = first.item;
        first = first.sig;
        n--;
        return i;
    }
    public Item dequeue() throws Exception
    {
        if (first == null)
            throw new Exception("Pila vacia");
        Item i = last.item;
        Nodo x = last;
        last = last.ant;
        last.sig = x.sig;
        n--;
        return i;
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
        return new IteradorPilaCola();
    }
    private class IteradorPilaCola implements Iterator<Item>
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
    
    public static void main(String[] args) throws Exception
    {
        PilaCola<Integer> p = new PilaCola<>();
        p.PushEnqueue(23);
        p.PushEnqueue(56);
        p.PushEnqueue(78);
        p.PushEnqueue(120);
        p.PushEnqueue(543);
        p.PushEnqueue(34);
        p.PushEnqueue(564444);
        p.PushEnqueue(98);

        StdOut.println(p.pop());
        StdOut.println(p.pop());
        for (Integer integer : p) 
        {
            StdOut.print(integer+" ");
        }
        StdOut.println("");

        StdOut.println(p.dequeue());
        StdOut.println(p.dequeue());
        for (Integer integer : p) 
        {
            StdOut.print(integer+" ");
        }
    }
}
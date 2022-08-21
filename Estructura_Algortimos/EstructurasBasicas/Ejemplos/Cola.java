import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class Cola<T> implements Iterable<T> {
    private class Nodo {
        T item;
        Nodo sig;
    }

    private Nodo first;
    private Nodo last;
    private int n;

    void enqueue(T item) {
        Nodo x = new Nodo();
        x.item = item;
        if(last!=null)
        {
            last.sig = x;
            last = x;
        }
        else
        {
            first = x;
            last = x;
        }
        n++;
    }

    T dequeue() throws Exception {
        if (first == null)
            throw new Exception("Cola vacia");
        T i = first.item;
        first = first.sig;
        n--;
        return i;
    }

    T popAlFinal()throws Exception
    {
        Nodo x = first;
        if(x==null)throw new Exception ("La pila esta vacia");
        if(n==1)
        {
            return dequeue();
        }
        else
        {
            while(x.sig.sig!=null) x = x.sig;
            Nodo ultimo = x.sig;
            x.sig = x.sig.sig;
            n--;
            return ultimo.item;
        }
         
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new IteradorCola();
    }
    private class IteradorCola implements Iterator<T>
    {
        private Nodo pos = first;

        @Override
        public boolean hasNext() {
            return pos!=null;
        }

        @Override
        public T next() {
            T item = pos.item;
            pos = pos.sig;
            return item;
        }
        
    }

    public static void main(String[] args) throws Exception {
        Cola<Integer> p = new Cola<>();

        for (int i = 0; i < 100; i++) {
            p.enqueue(i);
        }

        for (Integer integer : p) {
            StdOut.print(integer+" ");
        }

        StdOut.println("\n"+p.dequeue());
        StdOut.println(p.dequeue());

        StdOut.println(p.size());

        Cola<String> c = new Cola<>();
        c.enqueue("Hola");
        c.enqueue("Mundo");

        StdOut.println(c.dequeue());
    }
}
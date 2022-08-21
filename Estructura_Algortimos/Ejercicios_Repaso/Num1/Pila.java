import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class Pila<T> implements Iterable<T> {
    private class Nodo {
        T item;
        Nodo sig;
    }

    private Nodo first;
    private int n;

    void push(T item) {
        Nodo x = new Nodo();
        x.item = item;
        x.sig = first;
        first = x;
        n++;
    }

    T pop() throws Exception {
        if (first == null)
            throw new Exception("Pila vacia");
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
            return pop();
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
        return new IteradorPila();
    }
    private class IteradorPila implements Iterator<T>
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
        Pila<IPunto2D> p = new Pila<>();
        
        p.push(new Punto2DPolares(2, 45));
        p.push(new Punto2DCartesiano(1, 3));

        for (IPunto2D iPunto2D : p) 
        {
            StdOut.println(iPunto2D);   
        }
    }
}
import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class Pila<T> implements Iterable<T> {
    private class Nodo {
        T item;
        Nodo sig;
    }

    private Nodo first;
    private int n;

    public int getN ()
    {
        return n;
    }

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

    T peek() throws Exception
    {
        if (first == null)
            throw new Exception("Pila vacia");
        T i = first.item;
        return i;
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
        Pila<Integer> p = new Pila<>();
        String a = "22+35";
        String [] b = a.split("\\+");

        StdOut.print(b[0]+" ");
        StdOut.println(b[1]);

        for (int i = 0; i < 100; i++)
            p.push(i);

        StdOut.println(p.popAlFinal()+"\n");
        for (Integer i : p) 
        {
            StdOut.print(i+" ");
        }

        StdOut.println("\n"+p.size());

        Pila<Character> p2 = new Pila<>();
        p2.push('a');
        StdOut.println(p2.popAlFinal());

        Pila<Character> p3 = new Pila<>();
        p3.push('b');
        p3.push('c');
        StdOut.println(p3.peek()+" - "+p3.pop());

    }
}
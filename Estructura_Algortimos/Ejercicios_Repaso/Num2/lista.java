import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class lista<Item> implements Iterable<Item> {
    private class Nodo {
        Item item;
        Nodo sig;
    }

    private Nodo first;
    private int n;


    public void add(Item i) {
        Nodo x = new Nodo();
        x.item = i;
        x.sig = first;
        first = x;
        n++;
    }

    public Item remove() throws Exception {
        if (first == null)
            throw new Exception("La lista esta vacia");
        Item i = first.item;
        first = first.sig;
        n--;
        return i;
    }
    public Item removePos(int n) throws Exception{
        if(first == null)
            throw new Exception("La lista esta vacía");
        if(n>0 && n<=this.n)
        {
            if (n==1)
                return remove();
            else
            {
                Nodo x = first;
                int a = 1;
                while (a!= n-1)
                {
                    x = x.sig;
                    a++;
                }
                Nodo x2 = x.sig;
                x.sig = x2.sig;
                this.n--;
                return x2.item;
            }
        }
        else
            return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public lista<Item> listainversa(lista<Item> l) {
        lista<Item> l2 = new lista<>();
        for (Item i : l) {
            l2.add(i);
        }
        return l2;
    }
    public static lista listaaleatoria(lista l) throws Exception {
        lista l2 = new lista<>();
        int n;
        while(l.size()!=0)
        {
            n = (int)(Math.random()*l.size()+1);
            l2.add(l.removePos(n));
        }
        return l2;
    }


    @Override
    public Iterator<Item> iterator() 
    {
        return new IteradorLista();
    }
    private class IteradorLista implements Iterator<Item>
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
        lista<Integer> list = new lista<>();
        list.add(2);
        list.add(3);
        list.add(56);
        list.add(67);
        list.add(87);

        for (Integer i : list) 
        {
            StdOut.println(i);    
        }
        StdOut.println("");

        list = listaaleatoria(list);
        for (Integer i : list) 
        {
            StdOut.println(i);
        }
    }
}
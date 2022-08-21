import java.util.NoSuchElementException;

import javax.lang.model.util.ElementScanner6;

import edu.princeton.cs.algs4.*;
//Implementacion de la lista ordenada doblemente enlazada
public class ListaOrdenadaST <Key extends Comparable<Key>, Value>
{
    private int n;
    private Node first;
    private Node last;

    private class Node
    {
        private Key key;
        private Value val;
        private Node next;
        private Node ant;

        public Node (Key key, Value val)
        {
            this.key = key;
            this.val = val;
        }
    }

    public ListaOrdenadaST()
    {
    }
    public int size()
    {
        return n;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public boolean contains (Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        return get(key) != null;
    }
    public int rank(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        int lo = 1, hi = n;
        while(lo<=hi)
        {
            int mid = lo+(hi-lo)/2;
            Node x = first;
            for (int i = 2; i <= mid; i++) {
                x = x.next;
            }
            int cmp = key.compareTo(x.key);
            if(cmp<0)
                hi = mid-1;
            else if (cmp>0)
                lo = mid+1;
            else
                return mid;
        }
        return lo;
    }
    public void put (Key key, Value val)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(val == null)
        {
            delete(key);
            return;
        }
        int i = rank(key);
        Node x = first;
        for (int j = 2; j <= i && j<=n; j++) {
            x = x.next;
        }
        if(i<=n && x.key.compareTo(key)==0)
        {
            x.val = val;
            return;
        }
        Node t=new Node(key, val);
        if(i == 1)
        {
            t.next = x;
        }
        else if (i>n)
        {
            x.next = t;
            t.ant = x;
        }
        else
        {
            t.ant = x.ant;
            t.next = x;
        }
        x = t;
        if(n==0)
        {
            last = t;
            first = t;
        }
        if(i==1)
            first = x;
        else if (i > n)
            last = x;
        else
        {
            Node y = first;
            for (int j = 2; j <i; j++) {
                y = y.next;
            }
            y.next = x;
        }
        n++;
    }
    public Value get (Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(isEmpty())
            return null;
        int i = rank(key);
        Node x = first;
        for (int j = 2; j <=i; j++) {
            x=x.next;
        }
        if(i<=n && x.key.compareTo(key)==0)
            return x.val;
        return null;
    }
    public void delete(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(isEmpty())
            return;
        int i = rank(key);
        Node x = first;
        for (int j = 2; j <i; j++) {
            x = x.next;
        }
        if(i>n || x.next.key.compareTo(key)!=0 && i>1 || i==1 && x.key.compareTo(key)!=0)
            return;
        if(i==1)
        {
            first = x.next;
        }
        else
        {
            if(i==n)
                last = last.ant;
            x.next = x.next.next;
        }
        n--;
    }
    public Key select(int k)
    {
        if(k<=0 || k> size())
            throw new IllegalArgumentException("El argumento es invalido");
        Node x = first;
        for (int i = 2; i <=k; i++) {
            x = x.next;
        }
        return x.key;
    }
    public Key min()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        return first.key;
    }
    public Key max()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        return last.key;
    }
    public Iterable <Key> keys()
    {
        return keys(min(), max());
    }
    public Iterable <Key> keys(Key lo, Key hi)
    {
        if(lo == null)
            throw new IllegalArgumentException("La llave lo es nula");
        if (hi == null)
            throw new IllegalArgumentException("La llave hi es nula");
        Queue<Key> queue = new Queue<Key>();
        if(lo.compareTo(hi)>0)
            return queue;
        for (int i = rank(lo); i <= rank(hi); i++) 
        {
            queue.enqueue(select(i));    
        }
        return queue;
    }
    public boolean changeKey(Key oldkey, Key newkey)
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        int i = rank(oldkey);
        Node x = first;
        for (int j = 2; j <= i; j++) {
            x = x.next;
        }
        if(i>n || x.key.compareTo(oldkey)!=0)
            return false;
        int j = rank(newkey);
        if(j>i)
            //changeKey(x.next, j);
        //else if(j<i)
            //changeKey(x.ant, j);
        //else
            x.key = newkey;
        return true;
    }
    /*(public Node changeKey(Node x, int j)
    {
        if(x.next==null && j>rank(x.key) || x.ant == null && j<rank(x.key))
        {

        }
    }*/
    public static void main(String[] args) {
        ListaOrdenadaST<Integer, String> ls = new ListaOrdenadaST<>();
        ls.put(3, "Hola");
        ls.put(2, "Mundo");
        ls.put(4, "Perron");
        ls.put(5, "Mi");
        ls.put(7, "7");
        ls.put(6, "Te odio arquitectura");
        StdOut.println(ls.min()+" --- "+ls.max());
        ls.delete(7);
        for (Integer i : ls.keys()) {
            StdOut.print(i+" ");
        }
        for (int i = 0; i < 10; i++) {
            StdOut.println(i);
            if(i==5)
                break;
        }
    }
}

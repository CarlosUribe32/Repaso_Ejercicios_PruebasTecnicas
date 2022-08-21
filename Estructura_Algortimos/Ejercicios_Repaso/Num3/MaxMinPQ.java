import edu.princeton.cs.algs4.*;
import java.util.*;
public class MaxMinPQ <Key> implements Iterable<Key>
{
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxMinPQ (int initCapacity)
    {
        pq = (Key[]) new Object[initCapacity+1];
        n = 0;
    }
    public MaxMinPQ()
    {
        this(1);
    }
    public MaxMinPQ (int initCapacity, Comparator<Key> comparator)
    {
        this.comparator=comparator;
        pq = (Key[])new Object[initCapacity+1];
        n = 0;
    }
    public MaxMinPQ(Comparator<Key> comparator)
    {
        this(1, comparator);
    }
    public MaxMinPQ(Key[] keys)
    {
        n = keys.length;
        pq = (Key[])new Object[keys.length+1];
        for (int i = 0; i < n; i++) {
            pq[i+1] = keys[i];
        }
        for (int i = n/2; i >= 1; i--) {
            sink(i);
        }
        assert isMaxHeap();
    }

    public boolean isEmpty()
    {
        return n == 0;
    }
    public int size()
    {
        return n;
    }
    public Key max()
    {
        if(isEmpty())
            throw new NoSuchElementException("La cola de prioridad esta vacia");
        return pq[1];
    }
    public Key min()
    {
        if(isEmpty())
            throw new NoSuchElementException("La cola de prioridad esta vacia");
        Key min = pq[1];
        int j = 1;
        for (int i = 2; i <= n; i++) {
            if (less(i, j))
            {
                min = pq[i];
                j = i;
            }
        }
        return min;
    }
    private void resize(int capacity)
    {
        assert capacity>n;
        Key [] temp = (Key[])new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    public void insert(Key x)
    {
        if (n == pq.length-1)
            resize(2*pq.length);
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }
    public Key delMax()
    {
        if(isEmpty())
            throw new NoSuchElementException("La cola de prioridad esta vacia");
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null; //Innecesario segun el profe
        if((n>0) && (n == (pq.length-1)/4))
            resize(pq.length/2);
        assert isMaxHeap();
        return max;
    }
    public Key delMin()
    {
        if(isEmpty())
            throw new NoSuchElementException("La cola de prioridad esta vacia");
        Key min = min();
        for (int i = 1; i <= n; i++) {
            if(pq[i]==min)
            {
                exch(i, n--);
                sink(n);
                break;
            }
        }
        assert isMaxHeap();
        return min;
    }
    private void swim(int k)
    {
        while (k>1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }
    private void sink(int k)
    {
        while(2*k <= n)
        {
            int j = 2*k;
            if(j < n && less(j, j+1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }
    //Funciones complementarias
    private boolean less (int i, int j)
    {
        if (comparator == null)
            return ((Comparable<Key>)pq[i]).compareTo(pq[j]) <0;
        else
            return comparator.compare(pq[i], pq[j])<0;
    }
    private void exch(int i, int j)
    {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    private boolean isMaxHeap()
    {
        for (int i = 1; i <= n; i++) {
            if(pq[i]==null)
                return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if(pq[i] != null)
                return false;
        }
        if(pq[0]!= null)
            return false;
        return isMaxHeapOrdered(1);

    }
    private boolean isMaxHeapOrdered(int k)
    {
        if(k>n)
            return true;
        int left = 2*k;
        int right = 2*k+1;
        if(left <= n && less(k, left))
            return false;
        if(right <= n && less(k, right))
            return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);        
    }
    //Iterador
    public Iterator<Key> iterator()
    {
        return new HeapIterator();
    }
    private class HeapIterator implements Iterator<Key>
    {
        private MaxPQ<Key> copy;

        public HeapIterator()
        {
            if(comparator==null)
                copy = new MaxPQ<Key>(size());
            else
                copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }
        public boolean hasNext()
        {
            return !copy.isEmpty();
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        public Key next()
        {
            if(!hasNext())
                throw new NoSuchElementException();
            return copy.delMax();
        }
    }
    public static void main(String[] args) {
        MaxMinPQ<Integer> eo = new MaxMinPQ<>();
        eo.insert(1);
        eo.insert(4);
        eo.insert(2);
        eo.insert(0);
        eo.insert(5);
        eo.insert(9);
        eo.insert(8);
        StdOut.println(eo.delMax());
        StdOut.println(eo.delMin());
        StdOut.println(eo.delMin());
    }   
}

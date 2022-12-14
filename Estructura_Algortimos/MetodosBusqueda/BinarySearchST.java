import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
public class BinarySearchST<Key extends Comparable<Key>, Value>
{
    private static final int InitCapacity = 2;
    private Key[] keys;
    private Value[] vals;
    private int n=0;

    public BinarySearchST()
    {
        this(InitCapacity);
    }
    public BinarySearchST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity)
    {
        assert capacity >= n;
        Key [] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) 
        {
            tempk[i] = keys[i];
            tempv[i] = vals[i];    
        }
        vals = tempv;
        keys = tempk;
    }
    public int size()
    {
        return n;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public boolean contains(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        return get(key) != null;
    }
    public Value get(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(isEmpty())
            return null;
        int i = rank(key);
        if(i<n && keys[i].compareTo(key)==0)
            return vals[i];
        return null;
    }
    public int rank(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        int lo=0, hi=n-1;
        while(lo<=hi)
        {
            int mid = lo+(hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp <0)
                hi = mid - 1;
            else if (cmp>0)
                lo = mid +1;
            else
                return mid;            
        }
        return lo;
    }
    public void put(Key key, Value val)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(val == null)
        {
            delete(key);
            return;
        }
        int i = rank(key);
        if(i<n && keys[i].compareTo(key)==0)
        {
            vals[i] = val;
            return;
        }
        if(n == keys.length)
            resize(2*keys.length);
        for(int j = n; j>i; j--)
        {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
        assert check();
    }
    public void delete(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        if(isEmpty())
            return;
        int i = rank(key);
        if(i == n || keys[i].compareTo(key)!=0)
            return;
        for (int j = i; j< n-1; j++)
        {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        n--;
        keys[n] = null;
        vals[n] = null;
        if(n>0 && n == keys.length/4)
            resize(keys.length/2);
        assert check();
    }
    public void deleteMin()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        delete(min());
    }
    public void deleteMax()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        delete(max());
    }
    public Key min()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        return keys[0];
    }
    public Key max()
    {
        if(isEmpty())
            throw new NoSuchElementException("El buscador esta vacio");
        return keys[n-1];
    }
    public Key select(int k)
    {
        if(k<0 || k>= size())
            throw new IllegalArgumentException("El argumento es invalido");
        return keys[k];
    }
    public Key floor(Key key)
    {
        if(key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        int i = rank(key);
        if(i < n && key.compareTo(keys[i])==0)
            return keys[i];
        if (i == 0)
            return null;
        else
            return keys[i-1];
    }
    public Key ceiling (Key key)
    {
        if (key == null)
            throw new IllegalArgumentException("La llave dada es nula");
        int i = rank(key);
        if (i == n)
            return null;
        else
            return keys[i];
    }
    public int size (Key lo, Key hi)
    {
        if(lo == null)
            throw new IllegalArgumentException("La llave lo es nula");
        if (hi == null)
            throw new IllegalArgumentException("La llave hi es nula");
        if(lo.compareTo(hi)>0)
            return 0;
        if(contains(hi))
            return rank(hi) - rank(lo)+1;
        else
            return rank(hi) - rank(lo);
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
        for (int i = rank(lo); i < rank(hi); i++) 
        {
            queue.enqueue(keys[i]);    
        }
        if(contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }
    private boolean check()
    {
        return isSorted() && rankCheck();
    }
    private boolean isSorted()
    {
        for (int i = 1; i < size(); i++) {
            if(keys[i].compareTo(keys[i-1])<0)
                return false;
        }
        return true;
    }
    private boolean rankCheck()
    {
        for (int i = 0; i < size(); i++) {
            if(i != rank(select(i)))
                return false;
        }
        for (int i = 0; i < size(); i++) {
            if(keys[i].compareTo(select(rank(keys[i]))) != 0)
                return false;
        }
        return true;
    }
}

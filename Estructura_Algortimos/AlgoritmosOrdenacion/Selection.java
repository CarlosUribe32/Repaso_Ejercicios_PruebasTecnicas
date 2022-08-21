import java.util.*;
import edu.princeton.cs.algs4.*;
public class Selection
{
    private Selection(){} //Constructor privado para evitar ser instanciada la clase

    private static boolean less (Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    private static boolean less (Comparator comparator, Object v, Object w)
    {
        return comparator.compare(v,w)<0;
    }

    private static void exch(Object[]a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[]a)
    {
        return isSorted(a, 0, a.length-1);        
    }
    private static boolean isSorted(Comparable[]a, int lo, int hi)
    {
        for(int i = lo+1; i<=hi; i++)
        {
            if(less(a[i], a[i-1]))
                return false;
        }
        return true;
    }
    private static boolean isSorted(Object[]a, Comparator comparator)
    {
        return isSorted(a, comparator, 0, a.length-1);
    }
    private static boolean isSorted(Object[]a, Comparator comparator, int lo, int hi)
    {
        for(int i = lo+1;i<=hi;i++)
        {
            if(less(comparator, a[i], a[i-1]))
                return false;
        }
        return true;
    }

    public static void show(Comparable[]a)
    {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+" ");
        }
        StdOut.println("");
    }

    public static void sort(Comparable []a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++) 
        {
            int min = i;
            for (int j = i+1; j < n; j++) 
            {
                if(less(a[j], a[min]))
                    min=j;    
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);    
        }
        assert isSorted(a);
    }
    public static void sort(Object []a, Comparator comparator)
    {
        int n = a.length;
        for (int i = 0; i < n; i++) 
        {
            int min = i;
            for (int j = i+1; j < n; j++) 
            {
                if(less(comparator, a[j], a[min]))
                    min=j;    
            }
            exch(a, i, min);
            assert isSorted(a,comparator, 0, i);    
        }
        assert isSorted(a, comparator);
    }

    public static void main(String[] args) {
        String a = StdIn.readString();
        String[] b = new String[a.length()]; 
        for (int i = 0; i < a.length(); i++) {
            b[i]= a.charAt(i) + "";
        }
        Selection.sort(b);
        show(b);
    }
}

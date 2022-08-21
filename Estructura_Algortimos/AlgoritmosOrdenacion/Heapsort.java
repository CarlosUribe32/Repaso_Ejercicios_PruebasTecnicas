//Este es un algoritmo de ordenacion que usa las colas de prioridad (Metodos de seleccion)
import edu.princeton.cs.algs4.*;
public class Heapsort 
{
    private Heapsort()
    {    
    }
    public static void sort (Comparable [] pq)
    {
        int n = pq.length;
        for (int k = n/2; k>= 1; k--)
        {
            sink(pq, k, n);
        }
        int k = n;
        while(k>1)
        {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }
    private static void sink (Comparable[] pq, int k, int n)
    {
        while(2*k <= n)
        {
            int j = 2*k;
            if (j< n && less(pq, j, j+1))
                j++;
            if (!less(pq, k, j))
                break;
            exch(pq, k, j);
            k =j;
        }
    }
    private static boolean less (Comparable[] pq, int i, int j)
    {
        return pq[i-1].compareTo(pq[j-1])<0;
    }
    private static void exch(Object[] pq, int i, int j)
    {
        Object swap = pq[i-1];
        pq[i-1]= pq[j-1];
        pq[j-1]=swap;
    }
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+" ");
        }
        StdOut.println("");
    }
    public static void main(String[] args) {
        String a = StdIn.readString();
        String[] b = new String[a.length()]; 
        for (int i = 0; i < a.length(); i++) {
            b[i]= a.charAt(i) + "";
        }
        Quicksort.sort(b);
        show(b);
    }
}

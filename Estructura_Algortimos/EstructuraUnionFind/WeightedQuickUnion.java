import edu.princeton.cs.algs4.*;
public class WeightedQuickUnion 
{
    private int[] id;
    private int[] sz;
    private int n;

    //Se deberia tomar el nombre de la clase UF pero para este ejemplo es para identificar la solucion
    public WeightedQuickUnion(int n)throws Exception
    {
        if(n<=0) throw new Exception("No se puede inicializar con numeros negativos o 0");
        id = new int[n];
        sz = new int[n];
        this.n = n;
        for (int i = 0; i < id.length; i++) 
        {
            id[i] = i; 
            sz[i]=1;   
        }
    }

    public int find (int p)throws Exception
    {
        if(p<0 || p>=id.length) throw new Exception("Este nodo no se encuentra en el arreglo");
        int r = p;
        while(r!=id[r])
            r=id[r];
        return r;
    }
    public boolean connected(int p, int q)throws Exception
    {
        if(p<0 || p>=id.length || q<0 || q>=id.length) throw new Exception("Este nodo no se encuentra en el arreglo");
        
        return find(p)==find(q);
    }
    public void union(int p, int q)throws Exception
    {
        if(p<0 || p>=id.length || q<0 || q>=id.length) throw new Exception("Este nodo no se encuentra en el arreglo");
        int raizp = find(p);
        int raizq = find(q);

        if(raizp==raizq) return;
        
        if(sz[raizp]>sz[raizq])
        {
            id[raizq] = raizp;
            sz[raizp] += sz[raizq];
        }
        else
        {
            id[raizp] = raizq;
            sz[raizq] += sz[raizp];
        }
        n--;
    }
    public int count()
    {
        return n;
    }

    public static void main(String[] args) throws Exception
    {
        WeightedQuickUnion uf = new WeightedQuickUnion(10);
        
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);

        for (int i = 0; i < uf.id.length; i++) {
            StdOut.print(uf.id[i]+"  ");
        }
        StdOut.println("");
        StdOut.println(uf.count());
        for (int i = 0; i < uf.sz.length; i++) {
            StdOut.print(uf.sz[i]+"  ");
        }
        StdOut.println("");

        StdOut.println(uf.connected(6,7)+"\n"+uf.connected(1, 0));

    }
}
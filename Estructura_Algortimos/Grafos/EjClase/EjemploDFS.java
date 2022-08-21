import edu.princeton.cs.algs4.*;
public class EjemploDFS 
{
    public static void main(String[] args) 
    {
        Graph grafo = new Graph(6);
        grafo.addEdge(0, 1);
        grafo.addEdge(0, 3);
        grafo.addEdge(3, 1);
        grafo.addEdge(2, 5);
        grafo.addEdge(5, 4);
        grafo.addEdge(4, 1);
        DFS_Visitor dfs = new DFS_Visitor(grafo, 0, null);
        StdOut.println(dfs.esConexo());
        StdOut.println("Numero de componentes conexas: "+dfs.numComponentesCon());
    }    
}

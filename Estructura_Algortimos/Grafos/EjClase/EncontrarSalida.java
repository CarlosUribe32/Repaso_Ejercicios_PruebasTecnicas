import edu.princeton.cs.algs4.*;
public class EncontrarSalida 
{
    public static void main(String[] args)
    {
        Graph lab = new Graph(4*5);
        lab.addEdge(0,1);
        lab.addEdge(1,6);
        lab.addEdge(2,7);
        lab.addEdge(2,3);
        lab.addEdge(3,4);
        lab.addEdge(4,9);
        lab.addEdge(5,10);
        lab.addEdge(6,7);
        lab.addEdge(6,11);
        lab.addEdge(8,9);
        lab.addEdge(9,14);
        lab.addEdge(10,15);
        lab.addEdge(11,12);
        lab.addEdge(12,17);
        lab.addEdge(13,18);
        lab.addEdge(13,14);
        lab.addEdge(15,16);
        lab.addEdge(16,17);
        lab.addEdge(18,19);
        DFS_Visitor dfs = new DFS_Visitor(lab, 0, new Caminante());
        StdOut.print("Camino: ");
        for (Integer v : dfs.getCamino()) {
            StdOut.print("    "+v);
        }
        StdOut.println("");
    }

    public static class Caminante implements Visitor<Integer> 
    {
        private static boolean salida = false;
        public void visit(Integer vertex) 
        {
            StdOut.println("Visitando nodo: "+vertex);
            if (vertex==19)
            {
                StdOut.println("Encontre la salida!!!");
                salida = true;
            }
        }
        public static boolean getSalida()
        {
            return salida;
        }
    }
}

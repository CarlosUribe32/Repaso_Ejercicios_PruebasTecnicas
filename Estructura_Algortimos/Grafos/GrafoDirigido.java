import edu.princeton.cs.algs4.*;
//Ejemplo grafo dirigido
public class GrafoDirigido {
    public static void main(final String[] args) {
        final Digraph vuelos = new Digraph(6);
        vuelos.addEdge(0, 2);
        vuelos.addEdge(0, 1);
        vuelos.addEdge(2, 1);
        vuelos.addEdge(3, 2);
        vuelos.addEdge(4, 2);
        vuelos.addEdge(4, 1);
        vuelos.addEdge(4, 3);
        vuelos.addEdge(1, 4);

        for (int x : vuelos.adj(3)) {
            StdOut.println(x);
        }
        StdOut.println("Conexiones salientes 4: "+vuelos.outdegree(4));
        StdOut.println("Conexiones entrantes 4: "+vuelos.indegree(4));
    }
}

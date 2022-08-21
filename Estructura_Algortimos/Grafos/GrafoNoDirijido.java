import edu.princeton.cs.algs4.*;
//Ejemplo grafo no dirigido
public class GrafoNoDirijido{
    public static void main(final String[] args) {
        final Graph vuelos = new Graph(6);
        vuelos.addEdge(1, 2);
        vuelos.addEdge(2, 3);
        vuelos.addEdge(2, 4);
        vuelos.addEdge(4, 3);
        vuelos.addEdge(3, 0);
        vuelos.addEdge(5, 3);

        for (int x : vuelos.adj(3)) {
            StdOut.println(x);
        }
        StdOut.println("Conexiones nodo 3: "+vuelos.degree(3));
    }
}
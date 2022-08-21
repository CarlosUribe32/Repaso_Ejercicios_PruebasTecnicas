import java.util.*;
import edu.princeton.cs.algs4.*;
//Ejercicio aplicado en grafos
public class DigrafoConNombres {
    private Map<String, Integer> nombres = new HashMap<>();
    private Map<Integer, String> numeros = new HashMap<>();
    private Digraph graph;

    DigrafoConNombres(String [] nombre){
        graph = new Digraph(nombre.length);
        for (int i = 0; i < nombre.length; i++) {
            nombres.put(nombre[i], i);
            numeros.put(i, nombre[i]);
        }
    }
    public void addEdge(String desde, String hasta){
        graph.addEdge(nombres.get(desde), nombres.get(hasta));
    }
    public int indegree(String node){
        return graph.indegree(nombres.get(node));
    }
    public Iterable<String> adj (String node)
    {
        Bag<String> a = new Bag<>();
        for (int x : graph.adj(nombres.get(node))) {
            a.add(numeros.get(x));
        }
        return a;
    }
    public String getNombre(int n) throws Exception
    {
        if(!numeros.containsKey(n))
            throw new Exception("Numero invalido");
        return numeros.get(n);
    }

    public static void main(String[] args) {
        String[] ciudades = {"Medellin", "Bogota", "Cali"};
        final DigrafoConNombres vuelos = new DigrafoConNombres(ciudades);
        vuelos.addEdge("Medellin", "Bogota");
        vuelos.addEdge("Medellin", "Cali");
        vuelos.addEdge("Cali", "Bogota");

        for (String x : vuelos.adj("Medellin")) {
            StdOut.println(x);
        }
        StdOut.println(vuelos.indegree("Bogota"));
    }
}


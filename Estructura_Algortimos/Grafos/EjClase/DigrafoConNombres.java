import java.util.*;
import edu.princeton.cs.algs4.*;
//Mejora del ejercicio DigrafoConNombres
public class DigrafoConNombres{
    private Map<Integer, Bag<Integer>> nombres = new HashMap<>();
    private Map<Integer, String> vers = new HashMap<>();
    private Bag<Vertice> vertices = new Bag<>();
    private Bag<String> conex = new Bag<>();
    private int n;
    class Vertice{
        private int id;
        private String nombre;
        private int indegree;

        public Vertice(int id, String nombre){
            this.id = id;
            this.nombre = nombre;
            indegree=0;
        }
        public String getNombre(){
            return nombre;
        }
        public int getId(){
            return id;
        }
        public int getIndegree(){
            return indegree;
        }
        public void Outdegree(){
            indegree++;
        }
    }
    public DigrafoConNombres(){}
    public void addEdge(String desde, String hasta){
        int from=0, to=0;
        boolean esta = false;
        String s = desde+"-"+hasta;
        for (String c : conex) {
            if(c.equals(s)){
                esta = true;
                break;
            }
        }
        if(!esta){
            conex.add(s);
            for (Vertice vertice : vertices) {
                if(vertice.getNombre().equals(desde))
                    from = vertice.getId();
                else if(vertice.getNombre().equals(hasta))
                {
                    to = vertice.getId();
                    vertice.Outdegree();
                }
            }

            if(from==0){
                n++;
                Vertice v = new Vertice(n, desde);
                vertices.add(v);
                from = n;
            }
            if(to==0){
                n++;
                Vertice v = new Vertice(n, hasta);
                v.Outdegree();
                vertices.add(v);
                to = n;
            }
            if(!nombres.containsKey(from))
                    nombres.put(from, new Bag<Integer>());
            nombres.get(from).add(to);
            vers.put(from, desde);
            vers.put(to, hasta);
        }
    }
    public int outdegree(String node){
        int from=0;
        for (Vertice vertice : vertices) {
            if(vertice.getNombre().equals(node)){
                from = vertice.getId();
                break;
            }
        }
        if(from!=0)
            return nombres.get(from).size();
        else
            return from;
    }
    public int indegree(String node)
    {
        int to=0;
        for (Vertice vertice : vertices) {
            if(vertice.getNombre().equals(node)){
                to = vertice.getIndegree();
                break;
            }
        }
        return to;
    }
    public Iterable<String> adj(String node) throws Exception
    {
        int from = 0;
        for (Vertice vertice : vertices) {
            if(vertice.getNombre().equals(node)){
                from = vertice.getId();
                break;
            }
        }
        if(from == 0)
            throw new Exception("El vertice no esta");
        Bag<Integer> ady = nombres.get(from);
        Bag<String> a = new Bag<>();
        for (Integer i : ady) {
            a.add(vers.get(i));
        }
        return a;
    }
    public static void main(String[] args) throws Exception{
        
        DigrafoConNombres vuelos = new DigrafoConNombres();
        vuelos.addEdge("Medellin", "Bogota");
        vuelos.addEdge("Medellin", "Cali");
            vuelos.addEdge("Cali", "Bogota");

        for (String x : vuelos.adj("Medellin")) {
                StdOut.println(x);
        }
        StdOut.println(vuelos.indegree("Bogota"));
        
    }
}
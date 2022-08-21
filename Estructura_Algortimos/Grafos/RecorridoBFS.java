import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Graph;
//Recorrido en grafos
public class RecorridoBFS 
{
    
    public static void BFS(Graph g, int s) 
    {
        Queue<Integer> paraVisitar = new Queue<>();
        boolean[] marcados = new boolean[g.V()];
        paraVisitar.enqueue(s);
        marcados[s]=true;
        while(!paraVisitar.isEmpty()) 
        {
            Integer actual = paraVisitar.dequeue();
            StdOut.println("Visitado: "+actual);
            for(Integer vecino: g.adj(actual)) {
                if (!marcados[vecino]) {
                    marcados[vecino]=true;
                    paraVisitar.enqueue(vecino);
                }
            }
        }
    }
    public static void main(String[] args) {
        Graph g = GraphGenerator.simple(10,20);
        BFS(g,0);
    }
}

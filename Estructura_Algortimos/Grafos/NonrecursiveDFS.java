import java.util.Iterator;
import edu.princeton.cs.algs4.*;
//Recorrido en grafos
public class NonrecursiveDFS 
{
    private boolean[] marked;  // marked[v] = is there an s-v path?

    public NonrecursiveDFS(Graph G, int s) 
    {
        marked = new boolean[G.V()];

        validateVertex(s);
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) 
            {
                int w = adj[v].next();
                if (!marked[w]) 
                {
                    marked[w] = true;
                    stack.push(w);
                }
            }
            else 
            {
                stack.pop();
            }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}

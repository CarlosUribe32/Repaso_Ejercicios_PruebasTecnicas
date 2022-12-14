import edu.princeton.cs.algs4.*;
//Recorrido en grafos
public class DepthFirstSearch 
{
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) 
    {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) 
            {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) 
    {
        validateVertex(v);
        return marked[v];
    }

    public int count() 
    {
        return count;
    }

    private void validateVertex(int v)
    {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }    
}

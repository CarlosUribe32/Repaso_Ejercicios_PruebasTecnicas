import edu.princeton.cs.algs4.*;
public class DFS_Visitor {
    
        private boolean[] marked;    // marked[v] = is there an s-v path?
        private int count;           // number of vertices connected to s
        private int compCon;
        private boolean [] copyMarked;
    
        private Visitor<Integer> visitor;
        private Bag<Integer> camino = new Bag<>(); 
        
        public DFS_Visitor(Graph G, int s, Visitor<Integer> v)
        {
            marked = new boolean[G.V()];
            validateVertex(s);
            visitor = v;
            dfs(G, s);

            if(EncontrarSalida.Caminante.getSalida())
                return;
            //Imp comp conexas
            compCon++;
            copyMarked = marked;
            for (int i = 0; i < copyMarked.length; i++) {
                if(!copyMarked[i])
                {
                    compCon++;
                    dfs2(G, i);
                }
            }
        }
    
        private void dfs(Graph G, int v) 
        {
            if(EncontrarSalida.Caminante.getSalida())
                return;
            count++;
            marked[v] = true;
            if(visitor!=null)
                visitor.visit(v);
            for (int w : G.adj(v)) {
                if (!marked[w]) 
                {
                    dfs(G, w);
                }
            }
            if(EncontrarSalida.Caminante.getSalida())
                camino.add(v);
        }

        //Imp comp conexas
        private void dfs2 (Graph G, int v)
        {
            copyMarked[v] = true;
            if(visitor!=null)
                visitor.visit(v);
            for (int w : G.adj(v)) {
                if (!copyMarked[w]) 
                {
                    dfs2(G, w);
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
        public boolean esConexo()
        {
            return count == marked.length;
        }
        public int numComponentesCon()
        {
            return compCon;
        }
        public Bag<Integer> getCamino()
        {
            return camino;
        }
    
        private void validateVertex(int v)
        {
            int V = marked.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }    
    
}

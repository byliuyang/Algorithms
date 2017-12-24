package graphs.undirectedGraphs;

import libs.In;
import libs.StdOut;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        count++;

        for(int w: G.adj(s))
            if(!marked[w]) dfs(G, w);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++)
            if (depthFirstSearch.marked(v))
                StdOut.print(v + " ");
        StdOut.println();

        if(depthFirstSearch.count != G.V())
            StdOut.print("NOT ");
        StdOut.println(" connected");
    }
}

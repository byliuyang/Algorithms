package graphs.undirectedGraphs;

import libs.In;
import libs.StdOut;

/**
 * Two-colorability. Can the vertices of a given graph be assigned one of two colors in such a way that no edge connects
 * vertices of the same color?
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        isTwoColorable = true;

        for (int s = 0; s < G.V(); s++)
            if (!marked[s]) dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v])
                isTwoColorable = false;
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        TwoColor twoColor = new TwoColor(G);

        if(!twoColor.isTwoColorable()) StdOut.print("NOT ");
        StdOut.println("colorable");
    }
}

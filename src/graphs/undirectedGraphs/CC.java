package graphs.undirectedGraphs;

import fundamentals.bagQueueStack.bag.Bag;
import libs.In;
import libs.StdOut;

/**
 * Depth-first search to find connected components in a graph
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        id[s] = count;

        for (int w : G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];

        for (int i = 0; i < M; i++)
            components[i] = new Bag<>();

        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);

        for (int i = 0; i < M; i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}

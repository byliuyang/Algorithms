package graphs.directedGraphs;

import fundamentals.bagQueueStack.bag.Bag;
import libs.In;
import libs.StdOut;

public class DirectedDFS {
    private boolean[] marked;

    DirectedDFS(Digraph digraph, int source) {
        marked = new boolean[digraph.V()];
        dfs(digraph, source);
    }

    DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.V()];
        for (int source : sources) {
            if (!marked[source])
                dfs(digraph, source);
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w])
                dfs(digraph, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++)
            sources.add(Integer.parseInt(args[i]));

        DirectedDFS reachable = new DirectedDFS(digraph, sources);
        for (int v = 0; v < digraph.V(); v++)
            if (reachable.marked(v)) StdOut.print(v + " ");
        StdOut.println();
    }
}

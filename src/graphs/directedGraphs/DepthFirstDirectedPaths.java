package graphs.directedGraphs;

import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

public class DepthFirstDirectedPaths {
    private boolean marked[];
    private int source;
    private int[] parent;

    DepthFirstDirectedPaths(Digraph digraph, int source) {
        marked = new boolean[digraph.V()];
        parent = new int[digraph.V()];
        this.source = source;

        dfs(digraph, source);
    }

    private void dfs(Digraph digraph, int vertex) {
        marked[vertex] = true;
        for (int adjacent : digraph.adj(vertex))
            if (!marked[adjacent]) {
                parent[adjacent] = vertex;
                dfs(digraph, adjacent);
            }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) return null;

        Stack<Integer> path = new Stack<>();
        for (int x = vertex; x != source; x = parent[x])
            path.push(x);
        path.push(source);

        return path;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(digraph, s);

        for (int v = 0; v < digraph.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v))
                for (int x : paths.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}

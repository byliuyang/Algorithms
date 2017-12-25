package graphs.undirectedGraphs;

import fundamentals.bagQueueStack.queue.Queue;
import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

/**
 * Breadth-first search to find paths in a graph
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths paths = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v))
                for (int x : paths.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
            StdOut.println();
        }
    }
}

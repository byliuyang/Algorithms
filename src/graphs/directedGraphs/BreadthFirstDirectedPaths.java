package graphs.directedGraphs;

import fundamentals.bagQueueStack.queue.Queue;
import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

public class BreadthFirstDirectedPaths {
    private boolean[] marked;
    private int source;
    private int[] parent;

    BreadthFirstDirectedPaths(Digraph digraph, int source) {
        marked = new boolean[digraph.V()];
        parent = new int[digraph.V()];
        this.source = source;

        bfs(digraph, source);
    }

    private void bfs(Digraph digraph, int source) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            marked[vertex] = true;
            StdOut.println(vertex);
            for (int adjacent : digraph.adj(vertex)) {
                if (!marked[adjacent]) {
                    parent[adjacent] = vertex;
                    queue.enqueue(adjacent);
                }
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        Stack<Integer> stack = new Stack<>();
        for (int x = vertex; x != source; x = parent[x])
            stack.push(x);
        stack.push(source);
        return stack;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(digraph, s);

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

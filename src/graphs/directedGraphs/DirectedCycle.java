package graphs.directedGraphs;

import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

public class DirectedCycle {
    private int[] edgeTo;
    private boolean[] marked;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        onStack = new boolean[digraph.V()];

        for (int v = 0; v < digraph.V(); v++)
            if (!marked[v]) dfs(digraph, v);
    }

    private void dfs(Digraph digraph, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for (int adjacent : digraph.adj(vertex)) {
            if (hasCycle()) return;
            else if (!marked[adjacent]) {
                edgeTo[adjacent] = vertex;
                dfs(digraph, adjacent);
            } else {
                cycle = new Stack<>();
                for (int x = vertex; x != adjacent; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(adjacent);
                cycle.push(vertex);
            }
        }
        onStack[vertex] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        DirectedCycle directedCycle = new DirectedCycle(digraph);

        if (directedCycle.hasCycle()) {
            for (int x : directedCycle.cycle())
                StdOut.print(x + " ");
            StdOut.println();
        }
    }
}

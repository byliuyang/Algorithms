package graphs.directedGraphs;

import libs.StdOut;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleDetector = new DirectedCycle(G);
        if (!cycleDetector.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];

        SymbolDigraph symbolDigraph = new SymbolDigraph(filename, delimiter);

        Topological topological = new Topological(symbolDigraph.G());

        for (int v : topological.order())
            StdOut.println(symbolDigraph.name(v) + " ");
    }
}

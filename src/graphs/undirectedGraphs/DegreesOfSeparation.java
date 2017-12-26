package graphs.undirectedGraphs;

import libs.StdIn;
import libs.StdOut;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph(args[0], args[1]);

        String source = args[2];
        if (!symbolGraph.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        Graph G = symbolGraph.G();
        int s = symbolGraph.index(source);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(G, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (symbolGraph.contains(sink)) {
                int t = symbolGraph.index(sink);
                if (breadthFirstPaths.hasPathTo(t)) {
                    for (int x : breadthFirstPaths.pathTo(t))
                        StdOut.println("    " + symbolGraph.name(x));
                } else {
                    StdOut.println("Not connected");
                }
            } else {
                StdOut.println(sink + " not in database.");
            }
        }
    }
}

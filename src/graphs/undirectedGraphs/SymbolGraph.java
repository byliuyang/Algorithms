package graphs.undirectedGraphs;

import libs.In;
import libs.StdIn;
import libs.StdOut;
import searching.symbolTables.BinarySearchST;

public class SymbolGraph {
    private BinarySearchST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filename, String delim) {
        st = new BinarySearchST<>();
        In in = new In(filename);

        while (in.hasNextLine()) {
            String[] names = in.readLine().split(delim);
            for (String name : names)
                if (!st.contains(name))
                    st.put(name, st.size());
        }

        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;

        G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] names = in.readLine().split(delim);
            int v = st.get(names[0]);
            for (int i = 1; i < names.length; i++)
                G.addEdge(v, st.get(names[i]));
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];

        SymbolGraph symbolGraph = new SymbolGraph(filename, delim);
        Graph G = symbolGraph.G();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : G.adj(symbolGraph.index(source)))
                StdOut.println("    " + symbolGraph.name(w));
        }
    }
}

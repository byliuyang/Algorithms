package graphs.directedGraphs;

import fundamentals.bagQueueStack.bag.Bag;
import libs.In;
import libs.StdOut;

import java.util.Arrays;

public class Digraph {
    private Bag<Integer>[] adj;
    private int V;
    private int E;
    private static final String NEWLINE = System.getProperty("line.separator");

    public Digraph(int V) {
        this.V = V;
        this.E = 0;

        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                digraph.addEdge(w, v);
        return digraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges" + NEWLINE);

        for (int v = 0; v < V; v++) {
            s.append(v + ":");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        StdOut.println(digraph);
    }
}

package fundamentals.unionFind.weightedQuickUnion;

import fundamentals.unionFind.graphs.OperationGraphble;
import libs.StdIn;
import libs.StdOut;

/**
 * Weighted Quick-union union-find
 */
public class WeightedQuickUnionUF extends OperationGraphble {
    private int id[];  // parent link (site indexed)
    private int sz[];  // size of components (site indexed)
    private int count; // number of components

    /**
     * Initialize N sites with integer names (0 to N - 1)
     *
     * @param N
     */
    public WeightedQuickUnionUF(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for (int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();       // Read number of sites
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);  // Initialize N components

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();          // Read pair to connect
            if (uf.connected(p, q)) continue; // Ignore if connected
            uf.union(p, q);                   // Combine components
            StdOut.println(p + " " + q);      //   and print connection.
        }

        StdOut.println(uf.count() + " components");
    }

    /**
     * @return true if p and q are in the same component
     */
    public boolean connected(int p, int q) {
        incrementI();
        return find(p) == find(q);
    }

    /**
     * Quick-union
     *
     * @param p
     *
     * @return component identifier for p (0 to N - 1)
     */
    public int find(int p) {
        // Find component name

        // For stats
        incrementCost();
        incrementTotal();

        while (p != id[p]) {
            p = id[p];

            // For stats
            incrementCost();
            incrementTotal();
        }
        return p;
    }

    /**
     * Quick-union
     * Add connection between p and q
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) return;

        // Make smaller root point to larger one.

        // For stats
        incrementCost();
        incrementCost();

        incrementTotal();
        incrementTotal();

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];

            // For stats
            incrementCost();
            incrementCost();
            incrementCost();

            incrementTotal();
            incrementTotal();
            incrementTotal();
        } else {
            id[j] = i;
            sz[i] += sz[j];

            // For stats
            incrementCost();
            incrementCost();
            incrementCost();

            incrementTotal();
            incrementTotal();
            incrementTotal();
        }

        count--;
    }

    /**
     * @return number of components
     */

    public int count() {
        return count;
    }
}

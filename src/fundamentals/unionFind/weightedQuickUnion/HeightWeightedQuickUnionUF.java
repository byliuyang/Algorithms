package fundamentals.unionFind.weightedQuickUnion;

import libs.StdIn;
import libs.StdOut;

/**
 * Weighted Quick-union union-find by height
 */
public class HeightWeightedQuickUnionUF {
    private int id[];  // parent link (site indexed)
    private int ht[];  // height of components (site indexed)
    private int count; // number of components

    /**
     * Initialize N sites with integer names (0 to N - 1)
     *
     * @param N
     */
    public HeightWeightedQuickUnionUF(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
        ht = new int[N];
        for (int i = 0; i < N; i++)
            ht[i] = 0;
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();       // Read number of sites
        HeightWeightedQuickUnionUF uf = new HeightWeightedQuickUnionUF(N);  // Initialize N components

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
        while (p != id[p]) p = id[p];
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
        if (ht[i] < ht[j]) {
            id[i] = j;
            ht[j] = Math.max(ht[j], ht[i] + 1);
        } else {
            id[j] = i;
            ht[i] = Math.max(ht[i], ht[j] + 1);
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

package fundamentals.unionFind.quickUnion;

import fundamentals.unionFind.graphs.OperationGraphble;
import libs.StdIn;
import libs.StdOut;

/**
 * Quick Union Union-Find
 */
public class QuickUnionUF extends OperationGraphble {

    private int id[];  // parent link (site indexed)
    private int count; // number of components

    /**
     * Initialize N sites with integer names (0 to N - 1)
     *
     * @param N
     */
    public QuickUnionUF(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt(); // Read number of sites
        QuickUnionUF uf = new QuickUnionUF(N);       // Initialize N components

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();         // Read pair to connect
            if (uf.connected(p, q)) continue; // Ignore if connected
            uf.union(p, q);                  // Combine components
            StdOut.println(p + " " + q);     //   and print connection.
        }

        StdOut.println(uf.count() + " components");
    }

    /**
     * @return true if p and q are in the same component
     */
    public boolean connected(int p, int q) {
        // For stats
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
        // Give p and q the same root
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        // For stats
        incrementCost();
        incrementTotal();

        count--;
    }

    /**
     * @return number of components
     */

    public int count() {
        return count;
    }
}

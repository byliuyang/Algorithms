package fundamentals.unionFind.quickFind;

import fundamentals.unionFind.graphs.OperationGraphble;
import libs.StdIn;
import libs.StdOut;

/**
 * Quick Find Union-Find
 */
public class QuickFindUF extends OperationGraphble {

    private int id[];  // access to component id (site indexed)
    private int count; // number of components

    /**
     * Initialize N sites with integer names (0 to N - 1)
     *
     * @param N
     */
    public QuickFindUF(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt(); // Read number of sites
        QuickFindUF uf = new QuickFindUF(N);       // Initialize N components

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
        // Find component name
        // For Stats
        incrementCost();
        incrementTotal();
        return id[p];
    }

    /**
     * Quick-find
     * Add connection between p and q
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {

        // Put p and q into the same component
        int pID = find(p);
        int qID = find(q);

        // Nothing to do if p and q are already
        // in the same component

        if (pID == qID) return;

        // Rename p's component to q's name
        for (int i = 0; i < id.length; i++) {
            // For Stats
            incrementCost();
            incrementTotal();
            if (id[i] == pID) {
                id[i] = qID;
                // For Stats
                incrementCost();
                incrementTotal();
            }
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

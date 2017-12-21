package fundamentals.unionFind.quickUnion;

import libs.StdIn;
import libs.StdOut;

/**
 * Quick Union Union-Find with path compression
 */
public class QuickUnionPathCompressionUF {

    private int id[];  // parent link (site indexed)
    private int count; // number of components

    /**
     * Initialize N sites with integer names (0 to N - 1)
     *
     * @param N
     */
    public QuickUnionPathCompressionUF(int N) {
        // Initialize component id array
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt(); // Read number of sites
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(N);       // Initialize N components

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
        // Give p and q the same root
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        // Path compression
        compressPath(q, qRoot);
        compressPath(p, qRoot);

        id[pRoot] = qRoot;

        count--;
    }

    /**
     * Links every site on the paths from p to the root of
     * its tree to the root of the new tree
     *
     * @param p
     * @param root
     */
    private void compressPath(int p, int root) {
        while (p != id[p]) {
            p = id[p];
            id[p] = root;
        }
    }

    /**
     * @return number of components
     */

    public int count() {
        return count;
    }
}

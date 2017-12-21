package fundamentals.unionFind;

import fundamentals.unionFind.weightedQuickUnion.WeightedQuickUnionUF;
import libs.StdOut;

/**
 * Generates random pairs of integers between 0 and N-1,
 * calling connected() to determine if they are connected
 * and then union() if not (as in our development client),
 * looping until all sites are connected
 */
public class ErdosRenyi {
    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N = Integer.parseInt(args[0]); // Read number of sites
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);       // Initialize N components
        int count = 0; // number of connections

        while (uf.count() > 1) {
            int p = randInt(0, N - 1);
            int q = randInt(0, N - 1);         // Random pair to connect

            count++;

            if (uf.connected(p, q)) continue; // Ignore if connected
            uf.union(p, q);                  // Combine components
        }

        StdOut.println(count);
    }

    public static int randInt(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}

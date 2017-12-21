package fundamentals.unionFind.graphs;

import fundamentals.unionFind.weightedQuickUnion.WeightedQuickUnionUF;
import libs.StdDraw;
import libs.StdIn;

import java.awt.*;

/**
 * Graph for weighted quick union union-find
 */
public class WeightedQuickUnionUFGraph {
    public static void main(String[] args) {
        int N = StdIn.readInt();

        double[][] costs = new double[900][2];

        double max = Double.MIN_VALUE;

        WeightedQuickUnionUF qf = new WeightedQuickUnionUF(N);       // Initialize N components

        int i = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();         // Read pair to connect

            qf.clear();
            if (!qf.connected(p, q)) qf.union(p, q);                  // Combine components
            costs[i][0] = qf.getCostOp();
            costs[i][1] = qf.getAvgOp();

            max = Math.max(max, Math.max(costs[i][0], costs[i][1]));

            i++;
        }

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);

        for (i = 0; i < N; i++) {
            // cost
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(i, costs[i][0]);

            // average cost
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(i, costs[i][1]);
        }

        StdDraw.show();
    }
}

package fundamentals.unionFind;

import fundamentals.unionFind.weightedQuickUnion.WeightedQuickUnionPathCompressionUF;
import libs.StdDraw;

import java.awt.*;

/**
 * Use UnionFind to check connectivity and use StdDraw to
 * draw the connections as they are processed
 */
public class RandomGridDisplay {
    public static int PADDING = 15;

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        // Set up canvas
        int canvasWidth = PADDING * (N + 2);
        int canvasHeight = PADDING * (N + 2);
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();

        // Draw all sites
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, N + 1);
        StdDraw.setYscale(0, N + 1);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                StdDraw.point(i + 1, j + 1);

        StdDraw.show();

        // Generate connections
        RandomGrid randomGrid = new RandomGrid();
        Connection[] connections = randomGrid.generate(N);

        // Set up canvas pen
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.DARK_GRAY);

        // Initialize Quick Find Union-Find
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(N * N);

        // Connect all the sites
        for (Connection connection : connections) {
            int p = connection.p;
            int q = connection.q;

            if (uf.connected(p, q)) continue;
            uf.union(p, q);

            // Convert array index to coordinate
            Point start = new Point(p, N);
            Point end = new Point(q, N);

            // Draw connection on canvas
            StdDraw.line(start.x + 1, start.y + 1, end.x + 1, end.y + 1);

            StdDraw.show(T);
        }
    }
}

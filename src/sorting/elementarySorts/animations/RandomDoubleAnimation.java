package sorting.elementarySorts.animations;

import libs.StdDraw;
import libs.StdRandom;

import java.awt.*;

/**
 * Created by harryliu on 7/11/16.
 */
public class RandomDoubleAnimation {

    public static final int WIDTH       = 4;
    public static final int UNIT_HEIGHT = 50;
    public static final int PADDING     = 10;

    public static void show(Double[] a, int s, int e, int j) {
        // Clear the canvas
        StdDraw.clear();

        // Draw the array on the canvas
        for (int i = 0; i < a.length; i++) {
            if (i == j)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.RED);
            else if (i >= s && i <= e)
                // Highlight the touched part
                StdDraw.setPenColor(Color.BLACK);
            else StdDraw.setPenColor(Color.LIGHT_GRAY);

            // Draw the Double
            StdDraw.filledRectangle(PADDING + (2 * i + 0.5) * WIDTH, a[i] / 2 * UNIT_HEIGHT + PADDING, 0.5 * WIDTH, a[i] / 2.0 * UNIT_HEIGHT);
        }

        // Show the graph with 24 frames per second
        StdDraw.show(42);
    }

    public static void show(Double[] a, int s1, int e1, int s2, int e2, int j, int k) {
        // Clear the canvas
        StdDraw.clear();

        // Draw the array on the canvas
        for (int i = 0; i < a.length; i++) {
            if (i == j)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.RED);
            else if (i == k)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.BLUE);
            else if ((i >= s1 && i < e1) || (i >= s2 && i < e2))
                // Highlight the touched part
                StdDraw.setPenColor(Color.BLACK);

            else StdDraw.setPenColor(Color.LIGHT_GRAY);

            // Draw the Double
            StdDraw.filledRectangle(PADDING + (2 * i + 0.5) * WIDTH, a[i] / 2.0 * UNIT_HEIGHT + PADDING, 0.5 * WIDTH, a[i] / 2.0 * UNIT_HEIGHT);
        }

        // Show the graph with 24 frames per second
        StdDraw.show(42);
    }

    public static void show(Double[] a, int s1, int e1, int s2, int e2, int j, int k, int m) {
        // Clear the canvas
        StdDraw.clear();

        // Draw the array on the canvas
        for (int i = 0; i < a.length; i++) {
            if (i == j)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.RED);
            else if (i == k)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.MAGENTA);
            else if (i == m)
                // Highlight the moving Double
                StdDraw.setPenColor(Color.BLUE);
            else if ((i >= s1 && i < e1) || (i >= s2 && i < e2))
                // Highlight the touched part
                StdDraw.setPenColor(Color.BLACK);

            else StdDraw.setPenColor(Color.LIGHT_GRAY);

            // Draw the Double
            StdDraw.filledRectangle(PADDING + (2 * i + 0.5) * WIDTH, a[i] / 2.0 * UNIT_HEIGHT + PADDING, 0.5 * WIDTH, a[i] / 2.0 * UNIT_HEIGHT);
        }

        // Show the graph with 24 frames per second
        StdDraw.show(42);
    }

    public static Double[] randomDoubles(int N) {
        // Generate N Doubles
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        return a;
    }

    public static Double[] randomInts(int N) {
        // Generate N Integers
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++)
            a[i] = (double) StdRandom.uniform(1, 10) / 10;
        return a;
    }

    public static void initCanvas(Double[] a) {
        int N = a.length;

        // Find the max Double
        Double max = max(a);

        // Initialize canvas with padding
        StdDraw.setCanvasSize((2 * N - 1) * WIDTH + 2 * PADDING, (int) (max * UNIT_HEIGHT + 2 * PADDING));
        StdDraw.setXscale(0, (2 * N - 1) * WIDTH + 2 * PADDING);
        StdDraw.setYscale(0, max * UNIT_HEIGHT + 2 * PADDING);
        StdDraw.enableDoubleBuffering();
    }

    public static Double max(Double[] a) {
        int N = a.length;

        Double max = a[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, a[i]);
        }

        return max;
    }
}

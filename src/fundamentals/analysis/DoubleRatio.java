package fundamentals.analysis;

import libs.StdOut;
import libs.StdRandom;

/**
 * Predict performance and determine the approximate order
 * of growth of the running time
 */
public class DoubleRatio {
    public static void main(String[] args) {
        double prev = timeTrail(125);

        for (int N = 250; true; N += N) {
            double time = timeTrail(N);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }

    public static double timeTrail(int N) {
        int MAX = 1000000;
        int[] a = new int[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);

        StopWatch timer = new StopWatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }
}

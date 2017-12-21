package fundamentals.analysis;

import libs.StdOut;
import libs.StdRandom;

/**
 *
 */
public class DoubleTesting {
    public static void main(String[] args) {
        // Print table of running times
        for (int N = 250; true; N += N) {
            // Print time for problem size N
            double time = timeTrail(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }

    public static double timeTrail(int N) {
        // Time ThreeSum.count() for N random 6 digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        StopWatch timer = new StopWatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }
}

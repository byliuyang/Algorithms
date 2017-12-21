package fundamentals.analysis;

import libs.StdOut;
import libs.StdRandom;

/**
 * Estimate a running time of a program
 */
public class StopWatch {
    private final long start;

    /**
     * Create a stop watch
     */
    public StopWatch() {
        start = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-1000000, 1000000);
        StopWatch timer = new StopWatch();
        int cnt = ThreeSum.count(a);
        double time = timer.elapsedTime();
        StdOut.println(cnt + " triples " + time);
    }

    /**
     * Return elapsed time since creation
     *
     * @return
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}

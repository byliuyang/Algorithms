package fundamentals.analysis;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Find two values whose difference is no greater than
 * the difference of any other pair
 */
public class ClosestPair {
    public static void main(String[] args) {
        double[] a = In.readDoubles(args[0]);

        double[] pair = closest_pair(a);
        StdOut.printf("(%d, %d)\n", pair[0], pair[1]);
    }

    public static double[] closest_pair(double[] a) {

        int N = a.length;

        // No pair
        if (N < 2) return new double[]{};

        Arrays.sort(a);

        double min_diff = Math.abs(a[1] - a[0]);
        double first = a[0];
        double second = a[1];

        // Only one pair
        if (N < 3) return new double[]{first, second};

        for (int i = 2; i < N; i++) {
            double diff = Math.abs(a[i] - a[i - 1]);
            if (diff < min_diff) {
                min_diff = diff;
                first = a[i - 1];
                second = a[i];
            }
        }

        return new double[]{first, second};
    }
}

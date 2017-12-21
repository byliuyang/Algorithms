package fundamentals.analysis;

import libs.In;
import libs.StdOut;

/**
 * Created by harryliu on 7/7/16.
 */
public class FarthestPair {
    public static void main(String[] args) {
        double[] a = In.readDoubles(args[0]);

        double[] pair = farthest_pair(a);

        StdOut.printf("(%5.2f, %5.2f)\n", pair[0], pair[1]);
    }

    public static double[] farthest_pair(double[] a) {
        int N = a.length;

        if (N < 2) return new double[]{};

        double first = a[0];
        double second = a[1];

        if (N < 3) return new double[]{first, second};

        for (double n : a) {
            first = Math.min(n, first);
            second = Math.max(n, second);
        }

        return new double[]{first, second};
    }
}

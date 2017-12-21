package fundamentals.analysis;

import libs.StdOut;

/**
 * Find a local minimum: an index i such that
 * a[i] < a[i - 1] and a[i] < a[i + 1]
 */
public class ArrayLocalMin {
    public static void main(String[] args) {
        // Generate quadratic data set
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);

        int N = end - start + 1;

        int[] a = new int[N];

        for (int i = 0; i < N; i++)
            a[i] = (int) Math.pow(start + i, 2);

        StdOut.println(localMin(a));
    }

    public static int localMin(int[] a) {

        int N = a.length;

        int l = 0;
        int r = N - 1;
        int i = (l + r) / 2;

        while (l <= r && i - 1 >= 0 && i + 1 < N) {
            if (a[i] <= a[i - 1] && a[i] <= a[i + 1]) return i;

            if (a[i - 1] < a[i + 1]) r = i - 1;
            else if (a[i - 1] > a[i + 1]) l = i + 1;

            i = (l + r) / 2;
        }

        return -1;
    }
}

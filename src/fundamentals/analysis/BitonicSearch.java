package fundamentals.analysis;

import libs.StdOut;

/**
 * Determine whether a given integer is in the bitonic array
 */
public class BitonicSearch {
    public static void main(String[] args) {
        int q = Integer.parseInt(args[0]);

        int[] a = {1, 3, 4, 6, 8, 9, 10, 14, 11, 7, 2, -4, -9};

        StdOut.println(bitonicSearch(a, q));
    }

    public static int bitonicSearch(int[] a, int q) {

        int N = a.length;

        // Find the max
        int maxPos = binarySearchMax(a);

        if (q == a[maxPos]) return maxPos;
        else if (q > a[maxPos]) return -1;
        else {
            // Perform binary search on both sides
            int asc = binarySearchAscending(a, q, 0, maxPos - 1);
            return asc != -1 ? asc : binarySearchDescending(a, q, maxPos + 1, N - 1);
        }
    }

    public static int binarySearchMax(int[] a) {
        int N = a.length;

        int lo = 0;
        int hi = N - 1;

        while (lo <= hi) {
            int m = (hi + lo) / 2;

            if (a[m - 1] <= a[m] && a[m + 1] <= a[m]) return m;

            if (a[m - 1] <= a[m + 1]) lo = m + 1;
            else if (a[m - 1] > a[m + 1]) hi = m - 1;
        }

        return -1;
    }

    public static int binarySearchAscending(int[] a, int q, int lo, int hi) {

        while (lo <= hi) {
            int m = (hi + lo) / 2;

            if (a[m] == q) return m;
            else if (q > a[m]) lo = m + 1;
            else hi = m - 1;
        }

        return -1;
    }

    public static int binarySearchDescending(int[] a, int q, int lo, int hi) {

        while (lo <= hi) {
            int m = (hi + lo) / 2;

            if (a[m] == q) return m;
            else if (q < a[m]) lo = m + 1;
            else hi = m - 1;
        }

        return -1;
    }
}
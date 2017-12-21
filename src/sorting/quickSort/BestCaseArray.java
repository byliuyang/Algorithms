package sorting.quickSort;

import libs.StdOut;

/**
 * Produce best case array for QuickSort
 */
public class BestCaseArray {

    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate best case array for quick sort
        Integer[] a = best(N);
        // Print the array
        show(a);
    }

    public static Integer[] best(int N) {
        Integer[] a = new Integer[N];

        // Fill in the array
        for (int i = 0; i < N; i++)
            a[i] = i;

        best(a, 0, N - 1);

        return a;
    }

    private static void best(Integer[] a, int lo, int hi) {
        if (hi <= lo) return;

        int mid = (hi + lo) / 2;

        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        exch(a, mid, lo);
    }

    private static void exch(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Integer[] a) {
        // Print array elements, on a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Remove both bounds check in the inner while loop
 */
public class QuickSentinels {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate random array
        Integer[] a = randomInts(N);
        // Sort the array
        sort(a);
        // Test whether the array is sorted
        assert isSorted(a);
        // Print the array
        show(a);
    }

    public static Integer[] randomInts(int N) {
        // Generate random array element with
        // Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = N - i;
        return a;
    }

    public static void sort(Comparable[] a) {
        // Shuffle array preventing from worse case performance
        StdRandom.shuffle(a);

        // Find the maximum
        int m = max(a);
        // Put max at right end
        exch(a, a.length - 1, m);
        // Sort a[0..a.length-1] in ascending order
        sort(a, 0, a.length - 1);
    }

    private static int max(Comparable[] a) {
        // find the max element in the array
        int max = 0;
        for (int i = 1; i < a.length; i++)
            if (less(a[max], a[i])) max = i;
        return max;
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print the elements, on a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // Sort a[lo..hi] in ascending order
        // Array is sorted
        if (hi <= lo) return;

        // partition
        int j = partition(a, lo, hi);

        // Sort the left part a[lo..j-1]
        sort(a, lo, j - 1);
        // Sort the right part a[j+1..hi]
        sort(a, j + 1, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition array into a[lo..j-1], a[j], a[j+1..hi]

        // left and right scan indices
        int i = lo, j = hi + 1;

        // partitioning item
        Comparable v = a[lo];

        while (true) {
            // Scan right, left, check if can complete and exchange
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;

            if (i >= j) break;
            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // Exchange a[i] and a[j]
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

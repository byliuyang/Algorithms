package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Add median-of-5 partitioning to QuickSort
 */
public class QuickMedian5 {
    // cutoff for insertion sort
    private static final int CUTOFF = 7;

    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate random Integers
        Integer[] a = randomInts(N);
        // Sort the array
        sort(a);
        // Test the array is sorted
        assert isSorted(a);
        // Show the content of array
        show(a);
    }

    public static Integer[] randomInts(int N) {
        // Generate N Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }

    public static void sort(Comparable[] a) {
        // Sort a[0..a.length-1] in ascending order
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // cut off to insertion sort
        int n = hi - lo + 1;
        if (n <= CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }

        int m = median5(a, lo, lo + n / 4, lo + n / 2, lo + 3 * n / 4, hi);
        exch(a, m, lo);

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // partition a[lo..hi] by returning index j so that
        // a[lo..j-1] <= a[j] <= a[j+1..hi]
        // left and right scanning indices
        int i = lo, j = hi + 1;

        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;

            if (i >= j) break;
            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    private static int median5(Comparable[] a, int i, int j, int k, int p, int q) {
        // return index of median element between a[i], a[j] and a[k]
        Integer[] indices = {i, j, k, p, q};

        // Perform insertion sort on indices
        indicesInsertionSort(a, indices, 0, indices.length - 1);

        // median of five
        return indices[2];
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        // Perform insertion sort on a[lo..hi]
        for (int i = lo; i < hi; i++)
            for (int j = i + 1; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static void indicesInsertionSort(Comparable[] a, Integer[] indices, int lo, int hi) {
        // Perform insertion sort on a[indices[lo]..indices[hi]]
        for (int i = lo; i < hi; i++)
            for (int j = i + 1; j > lo && less(a[indices[j]], a[indices[j - 1]]); j--)
                exch(indices, j, j - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // Exchange a[i] and a[j]
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print elements, on a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

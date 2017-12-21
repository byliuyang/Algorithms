package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Quicksort
 */
public class Quick {
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
        // Generate random Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }

    public static void sort(Comparable[] a) {
        // Eliminate dependencies on input
        StdRandom.shuffle(a);

        sort(a, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print the elements, in a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // Array is sorted
        if (hi <= lo) return;

        // Partition
        int j = partition(a, lo, hi);

        // Sort the left part a[lo...j]
        sort(a, lo, j - 1);
        // Sort the right part a[j + 1...hi]
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi]
        // left and right scan indices
        int i = lo, j = hi + 1;
        // partitioning item
        Comparable v = a[lo];
        while (true) {
            // Scan right, scan left, check for can complete, and exchange
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        // Put v = a[j] into position
        exch(a, lo, j);
        // with a[lo..j - 1] <= a[j] <= [j + 1..hi]
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // Exchange a[i] and a[j]
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

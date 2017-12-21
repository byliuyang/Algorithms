package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Fast entropy optimal sort
 */
public class Quick3wayFast {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate random integer
        Integer[] a = randomInts(N);
        // Sort the array
        sort(a);
        // Test whether the array is sorted
        assert isSorted(a);
        // Print the array
        show(a);
    }

    public static Integer[] randomInts(int N) {
        // Generate N Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }

    public static void sort(Comparable[] a) {
        // Shuffle preventing from worse case performance
        StdRandom.shuffle(a);
        // Sort the array in ascending order
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // Array is sorted
        if (hi <= lo) return;

        // a[lo..p-1] = v and a[q+1..hi] = v
        // a[p..i-1] < v and a[j+1..q] > v
        int p = lo, q = hi + 1;
        // a[i..j] are not scanned
        int i = lo, j = hi + 1;

        // partitioning element
        Comparable v = a[lo];

        while (true) {

            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

            // pointers cross
            if (i == j && eq(a[i], v)) exch(a, ++p, i);

            if (i >= j) break;

            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
        }

        i = j + 1;

        for (int k = lo; k <= p; k++)
            exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            exch(a, k, i++);

        sort(a, lo, j);
        sort(a, i, hi);
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

    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    public static void show(Comparable[] a) {
        // Print the elements, in a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        // Test out whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
}

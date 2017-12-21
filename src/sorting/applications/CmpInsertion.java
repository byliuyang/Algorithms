package sorting.applications;

import libs.In;
import libs.StdOut;

import java.util.Comparator;

/**
 * CmpInsertion sort
 */
public class CmpInsertion {
    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        // Sort a[] into increasing order
        int N = a.length;
        for (int i = 1; i < N; i++)
            // Insert a[i] among a[i - 1], a[i - 2], a[i - 3]...
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void sort(Object[] a, Comparator c) {
        // Sort a[] into increasing order
        int N = a.length;
        for (int i = 1; i < N; i++)
            // Insert a[i] among a[i - 1], a[i - 2], a[i - 3]...
            for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    public static boolean check(Comparable[] a) {
        int N = a.length;

        // Make a copy of original array
        Comparable[] o = new Comparable[N];
        for (int i = 0; i < N; i++)
            o[i] = a[i];

        // Sort the array
        sort(a);

        // Check whether the array is sorted
        if (!isSorted(a)) return false;

        // Check the set of objects did not change
        for (int i = 0; i < N; i++) {
            boolean exist = false;
            for (int j = 0; j < N; j++) {
                // Find the object
                if (a[i] == o[j]) {
                    exist = true;
                    break;
                }
            }
            if (!exist) return false;
        }

        return false;
    }
}

package sorting.mergeSort;

import libs.In;
import libs.StdOut;

/**
 * Faster top-down merge sort
 */
public class MergeFaster {

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        // Sort the left half
        sort(a, aux, lo, mid);
        // sort the right half
        sort(a, aux, mid + 1, hi);
        // Merge results
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Merge a[i...mid] with a[mid + 1...hi].

        int i = lo, j = hi;

        // Copy a[lo...mid] to aux[lo...mid]
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }

        // Cope a[mid + 1...hi] to aux[hi...mid + 1]
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi - k + mid + 1];
        }

        // Merge back to a[lo...hi].
        for (int k = lo; k <= hi; k++)
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j--];

    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        // allocate space just once
        // auxiliary array for merges
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print elements, in a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
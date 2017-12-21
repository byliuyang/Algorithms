package sorting.mergeSort;

import libs.In;
import libs.StdOut;

/**
 * Top-down merge sort with improvements
 */
public class MergeImproved {

    private static final int INSERTIONSORT_THRESHOLD = 7;

    private static void sort(Comparable[] src, Comparable[] dest, int lo, int hi) {
        if (hi <= lo) return;

        if (hi - lo < INSERTIONSORT_THRESHOLD) {
            // Cut off for small sub array
            insertionSort(dest, lo, hi);
            return;
        }

        int mid = (lo + hi) >>> 1;
        // Sort the left half
        sort(dest, src, lo, mid);
        // sort the right half
        sort(dest, src, mid + 1, hi);
        // Merge results
        merge(src, dest, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
        // Array is already in order
        if (less(src[mid], src[mid + 1])) return;

        // Merge a[i...mid] with a[mid + 1...hi].
        int i = lo, j = mid + 1;

        // Merge back to a[lo...hi].
        for (int k = lo; k <= hi; k++)
            if (i > mid) dest[k] = src[j++];
            else if (j > hi) dest[k] = src[i++];
            else if (less(src[j], src[i])) dest[k] = src[j++];
            else dest[k] = src[i++];
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        // Size of array
        int N = a.length;

        // allocate space just once
        // auxiliary array for merges
        Comparable[] aux = new Comparable[N];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];

        sort(aux, a, 0, a.length - 1);
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
}
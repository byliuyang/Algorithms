package sorting.mergeSort;

import libs.In;
import libs.StdOut;

/**
 * Bottom-up merge sort
 */
public class MergeBU {

    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        // Do lg N passes of pairwise merges.
        int N = a.length;
        // auxiliary array for merges
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz)
            // Sub array size
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                // Sub array index
                merge(a, aux, lo, lo + sz - 1, lo + sz + sz - 1);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Merge a[lo...mid] with a[mid + 1...hi]
        int i = lo, j = mid + 1;

        // Copy a[lo...hi] to aux[lo...hi]
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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

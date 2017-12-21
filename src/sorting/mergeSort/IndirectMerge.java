package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Sort array index in ascending order
 */
public class IndirectMerge {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);

        // Generate array
        Integer[] a = array(N);

        // Sort array indices
        int[] indices = sort(a);

        // Make sure array is sorted
        assert isSorted(a, indices);

        // Print the array
        show(a, indices);
    }

    public static Integer[] array(int N) {
        // Generate random Integers
        Integer[] a = new Integer[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);

        return a;
    }

    public static int[] sort(Comparable[] a) {
        // Size of array
        int N = a.length;
        // Array for indices
        int[] indices = new int[N];

        for (int i = 0; i < N; i++)
            indices[i] = i;

        // Auxiliary indices
        int[] aux = new int[N];

        // Sort the array indices
        sort(a, aux, indices, 0, N - 1);

        return indices;
    }

    public static boolean isSorted(Comparable[] a, int[] indices) {
        // Check whether the array indices are sorted
        int N = a.length;
        for (int i = 1; i < N; i++)
            if (less(a[indices[i]], a[indices[i - 1]])) return false;
        return true;
    }

    public static void show(Comparable[] a, int[] indices) {
        // Print the items, in a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[indices[i]] + " ");
        StdOut.println();
    }

    private static void sort(Comparable[] a, int[] aux, int[] indices, int lo, int hi) {
        // The array is sorted
        if (hi <= lo) return;

        // Cut the array into two halves
        int mid = (hi + lo) / 2;

        // Sort the left half
        sort(a, aux, indices, lo, mid);
        // Sort the right half
        sort(a, aux, indices, mid + 1, hi);
        // Merge left and right halves
        merge(a, aux, indices, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int[] aux, int[] indices, int lo, int mid, int hi) {

        // Copy array indices into auxiliary indices array
        for (int k = lo; k <= hi; k++)
            aux[k] = indices[k];

        // heads of two array
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) indices[k] = aux[j++];
            else if (j > hi) indices[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) indices[k] = aux[j++];
            else indices[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

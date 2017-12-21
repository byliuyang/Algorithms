package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Natural merge sort.
 */
public class NaturalMerge {
    public static void main(String[] args) {
        // number of elements
        int N = Integer.parseInt(args[0]);

        // Generate random Integer
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-N, N);

        // Sort the array
        sort(a);

        // Make sure array is sorted
        assert isSorted(a);

        // print the content of array
        show(a);
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void sort(Comparable[] a) {
        // Size of array
        int N = a.length;

        // auxiliary array for merge
        Comparable[] aux = new Comparable[a.length];

        int mid, hi;

        while (true) {
            // Find the first sorted block
            mid = findSortedBlockFrom(a, 0);

            if (mid == a.length - 1)
                // Array is sorted
                return;

            // Find the second sorted block
            hi = findSortedBlockFrom(a, mid + 1);

            // Merge two blocks
            merge(a, aux, 0, mid, hi);
        }
    }

    private static int findSortedBlockFrom(Comparable[] a, int i) {
        int N = a.length;

        // go through the array and find the sorted block
        while (i < N - 1 && !less(a[i + 1], a[i])) i++;
        return i;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Copy element to auxiliary
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
}

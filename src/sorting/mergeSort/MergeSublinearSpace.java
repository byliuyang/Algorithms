package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Top-down merge sort
 */
public class MergeSublinearSpace {

    public static void main(String[] args) {
        // Array size
        int N = Integer.parseInt(args[0]);
        // Block size
        int M = Integer.parseInt(args[1]);

        // Exit when N is not multiple of M
        if (N % M != 0) {
            StdOut.println("N should be multiple of M");
            return;
        }

        // Generate random Double array
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);

        // Sort the array
        sort(a, M);

        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a, int M) {
        // Size of the array
        int N = a.length;

        // allocate space just once
        // auxiliary array for merges
        Comparable[] aux = new Comparable[M];

        for (int i = 0; i < N; i += M)
            selectionSort(a, i, i + M - 1);

        while (!isSorted(a)) for (int i = 0; i < N; i += M)
            merge(a, aux, i, i + M - 1, Math.min(i + M + M - 1, N - 1));
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the  array entries are in order.
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

    private static void selectionSort(Comparable[] a, int lo, int hi) {
        // Sort a[lo...hi]
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int M = aux.length;
        for (int i = 0; i < M; i++) {
            aux[i] = a[lo + i];
        }

        int p = 0;
        int j = mid + 1;

        for (int k = lo; k <= hi && k < j && p < M; k++) {
            if (j > hi) a[k] = aux[p++];
            else if (less(aux[p], a[j])) a[k] = aux[p++];
            else a[k] = a[j++];
        }
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
}
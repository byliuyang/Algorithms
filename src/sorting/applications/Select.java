package sorting.applications;

import libs.StdRandom;

/**
 * Selecting the k smallest element in a[]
 */
public class Select {
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j == k) return a[k];
            else if (j > k) hi = j - 1;
            else lo = j + 1;
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static Comparable selectRec(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        return selectRec(a, k, lo, hi);
    }

    private static Comparable selectRec(Comparable[] a, int k, int lo, int hi) {
        if (lo >= hi) return a[k];

        int j = partition(a, lo, hi);
        if (j == k) return a[k];
        else if (j > k) return selectRec(a, k, lo, j - 1);
        else return selectRec(a, k, lo + 1, j);
    }
}

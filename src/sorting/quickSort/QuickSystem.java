package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Fast entropy optimal sort
 */
public class QuickSystem {
    // cut off to insertion sort
    private static final int INSERTION_SORT_CUTOFF = 8;
    // cut off to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF    = 40;

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
        // Sort the array in ascending order
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;

        // cutoff to insertion sort
        if (n <= INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        // use medium-of-3 as partitioning element
        else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo + n / 2, hi);
            exch(a, lo, m);
        }
        // use Tukey ninther as partitioning element
        else {
            int esp = n / 8;
            int mid = n / 2 + lo;
            int m1 = median3(a, lo, lo + esp, lo + esp + esp);
            int m2 = median3(a, mid - esp, mid, mid + esp);
            int m3 = median3(a, hi - esp - esp, hi - esp, hi);

            int ninther = median3(a, m1, m2, m3);
            exch(a, lo, ninther);
        }

        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;

        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

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

    private static int median3(Comparable[] a, int i, int j, int k) {
        // return index of median element between a[i], a[j] and a[k]
        return less(a[i], a[j]) ? (less(a[j], a[k]) ? j : (less(a[i], a[k]) ? k : i)) : (less(a[k], a[j]) ? j : (less(a[k], a[i]) ? k : i));
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            for (int j = i + 1; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
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

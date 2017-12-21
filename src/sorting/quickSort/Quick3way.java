package sorting.quickSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Quicksort with 3-way partitioning
 */
public class Quick3way {

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
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;

        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        // Now a[lo..lt - 1] < a[lt..gt] < a[gt + 1..hi]
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static boolean isSorted(Comparable[] a) {
        // Test out whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
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

    public static void show(Comparable[] a) {
        // Print the elements, in a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

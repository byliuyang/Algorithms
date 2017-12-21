package sorting.quickSort;

import libs.StdRandom;
import sorting.elementarySorts.animations.RandomDoubleAnimation;

/**
 * RandomDoubleAnimation for insertion sort
 */
public class QuickAnimation extends RandomDoubleAnimation {

    public static void main(String[] args) {
        // number of Doubles
        int N = Integer.parseInt(args[0]);

        // Generate N Doubles
        Double[] a = randomDoubles(N);

        // Initialize canvas
        initCanvas(a);

        // Show the array before sort
        show(a, -1, 0, -1);
        // Sort the array
        sort(a);
        // Show the result
        show(a, N, -1, -1);
    }

    public static void sort(Double[] a) {
        // Eliminate dependencies on input
        StdRandom.shuffle(a);

        sort(a, 0, a.length - 1);
    }

    private static void sort(Double[] a, int lo, int hi) {
        // Array is sorted
        if (hi <= lo) return;

        // Partition
        int j = partition(a, lo, hi);

        // Sort the left part a[lo...j]
        sort(a, lo, j - 1);
        // Sort the right part a[j + 1...hi]
        sort(a, j + 1, hi);
    }

    private static int partition(Double[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi]

        // left and right scan indices
        int i = lo, j = hi + 1;

        // partitioning item
        Double v = a[lo];


        while (true) {
            // Scan right, scan left, check for can complete, and exchange
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            show(a, lo, i, j, hi, i, j);
            exch(a, i, j);
        }

        // Put v = a[j] into position
        exch(a, lo, j);

        // with a[lo..j - 1] <= a[j] <= [j + 1..hi]
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // Exchange a[i] and a[j]
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

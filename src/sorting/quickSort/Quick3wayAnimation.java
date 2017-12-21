package sorting.quickSort;

import libs.StdRandom;
import sorting.elementarySorts.animations.RandomDoubleAnimation;

/**
 * RandomDoubleAnimation for insertion sort
 */
public class Quick3wayAnimation extends RandomDoubleAnimation {

    public static void main(String[] args) {
        // number of Doubles
        int N = Integer.parseInt(args[0]);

        // Generate N Doubles
        Double[] a = randomInts(N);

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
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;

        Double v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;

            show(a, lo, i, gt, hi, lt, gt, i);
        }
        // Now a[lo..lt - 1] < a[lt..gt] < a[gt + 1..hi]
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        // Exchange a[i] and a[j]
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

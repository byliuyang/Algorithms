package sorting.mergeSort;

import sorting.elementarySorts.animations.RandomDoubleAnimation;

/**
 * RandomDoubleAnimation for insertion sort
 */
public class MergeAnimation extends RandomDoubleAnimation {

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

    public static void merge(Double[] a, Double[] aux, int lo, int mid, int hi) {

        // Merge a[i...mid] with a[mid + 1...hi].
        int i = lo, j = mid + 1;

        // Copy a[lo...hi] to aux[lo...hi]
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        // Merge back to a[lo...hi].
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                show(a, lo, hi, j);
                a[k] = aux[j++];
            } else if (j > hi) {
                show(a, lo, hi, i);
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                show(a, lo, hi, j);
                a[k] = aux[j++];
            } else {
                show(a, lo, hi, i);
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(Double[] a) {
        // allocate space just once
        // auxiliary array for merges
        Double[] aux = new Double[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Double[] a, Double[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        // Sort the left half
        sort(a, aux, lo, mid);
        // sort the right half
        sort(a, aux, mid + 1, hi);
        // Merge results
        merge(a, aux, lo, mid, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

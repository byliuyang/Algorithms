package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Compute number of inversions in the array
 */
public class Inversions {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate random integer array
        Integer[] a = array(N);
        // Print the number of inversion in array
        StdOut.println(count(a));
    }

    public static Integer[] array(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static long count(Comparable[] a) {
        // Size of array
        int N = a.length;
        // auxiliary array for sorting
        Comparable[] aux = new Comparable[N];
        return count(a, aux, 0, N - 1);
    }

    private static long count(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // Sort a[lo...hi]
        if (hi <= lo) return 0;
        int mid = (hi + lo) / 2;
        // count number of inversions
        long count = 0;
        // Count number of inversions in left half
        count += count(a, aux, lo, mid);
        // Count number of inversions in right half
        count += count(a, aux, mid + 1, hi);
        // Count number of inversions in the merge array
        count += merge(a, aux, lo, mid, hi);
        return count;
    }

    private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Array is sorted without inversion
        if (less(a[mid], a[mid + 1])) return 0;
        // Copy elements to auxiliary array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        // beginning of left half and right half
        int i = lo, j = mid + 1;
        // count number of inversions
        long count = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                // all number in left half greater than a[i] including
                // itself have inversions with a[j]
                count += mid - i + 1;
            } else a[k] = aux[i++];
        }
        return count;
    }

    public static void show(Comparable[] a) {
        // Print elements, in a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

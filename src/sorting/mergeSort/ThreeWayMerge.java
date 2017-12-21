package sorting.mergeSort;

import libs.StdOut;

/**
 * Divide array into thirds, sort each third,
 * and combine using a 3-way merge
 */
public class ThreeWayMerge {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);

        // Generate random integer array
        Integer[] a = randomInts(N);

        // Sort the array
        sort(a);

        // Test whether array is sorted
        assert isSorted(a);
        // Show the content of array
        show(a);
    }

    public static Integer[] randomInts(int N) {
        // Generate random list of integers between 0 and N
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = N - i;
        return a;
    }

    public static void sort(Comparable[] a) {
        // Size of array
        int N = a.length;

        // Auxiliary array for merge
        Comparable[] aux = new Comparable[N];

        // Sort a[0...N - 1]
        sort(a, aux, 0, N - 1);
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array is sorted
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print the elements, in a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void show(Comparable[] a, int lo, int hi) {
        // Print the elements, in a single line
        for (int i = lo; i <= hi; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // array is sorted
        if (hi <= lo) return;

        // Size of sub array
        int N = (hi - lo) / 3;

        // One third position
        int third1 = lo + N;
        // Two third position
        int third2 = third1 + N;

        // Sort sub arrays
        sort(a, aux, lo, third1);
        sort(a, aux, third1 + 1, third2);
        sort(a, aux, third2 + 1, hi);

        // Merge the sorted sub arrays
        merge(a, aux, lo, third1, third2, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int third1, int third2, int hi) {

        // Array is sorted
        if (third1 <= lo && hi <= third2) return;

        // Array is sorted
        if (!less(a[third1 + 1], a[third1]) && !less(a[third2 + 1], a[third2])) return;

        // Copy elements onto auxiliary array
        for (int m = lo; m <= hi; m++)
            aux[m] = a[m];

        // Keep the start of sub arrays
        int i = lo, j = third1 + 1, k = third2 + 1;

        for (int m = lo; m <= hi; m++) {
            if (i > third1) {
                // The first block exhausted
                if (j > third2)
                    // The second block exhausted
                    a[m] = aux[k++];
                else if (k > hi)
                    // The third block exhausted
                    a[m] = aux[j++];
                else if (less(aux[j], aux[k])) a[m] = aux[j++];
                else a[m] = aux[k++];
            } else if (j > third2) {
                // The second block exhausted
                if (i > third1)
                    // The first block exhausted
                    a[m] = aux[k++];
                else if (k > hi)
                    // The third block exhausted
                    a[m] = aux[i++];
                else if (less(aux[i], aux[k])) a[m] = aux[i++];
                else a[m] = aux[k++];
            } else if (k > hi) {
                // The third block exhausted
                if (i > third1)
                    // The first block exhausted
                    a[m] = aux[j++];
                else if (j > third2)
                    // The second block exhausted
                    a[m] = aux[i++];
                else if (less(aux[i], aux[j])) a[m] = aux[i++];
                else a[m] = aux[j++];
            } else if (less(aux[i], aux[j]) && less(aux[i], aux[k])) a[m] = aux[i++];
            else if (less(aux[j], aux[i]) && less(aux[j], aux[k])) a[m] = aux[j++];
            else a[m] = aux[k++];
        }
    }

    private static boolean lessEq(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

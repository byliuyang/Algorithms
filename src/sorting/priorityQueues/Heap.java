package sorting.priorityQueues;

import libs.StdOut;
import libs.StdRandom;

/********************************************************************
 *
 * Compilation: javac Heap.java
 * Execution: java HeapSort N
 * Dependencies: StdRandom.java StdOut.java
 *
 * HeapSort by heap construction and sort down
 *
 ********************************************************************/

public class Heap {

    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate N random Integers
        Integer[] a = randomInts(N);
        // Sort the array
        sort(a);
        // Test whether array is sorted
        assert isSorted(a);
        // Print array elements
        show(a);
    }

    /**
     * Return N random Integers between 0 and <tt>N - 1</tt>
     *
     * @param N size of array
     *
     * @return N random Integers between 0 and <tt>N - 1</tt>
     */
    public static Integer[] randomInts(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);

        return a;
    }

    /**
     * Return <tt>true</tt> if array a is sorted in ascending order, <tt>false</tt> otherwise
     *
     * @param a array to be asserted
     *
     * @return <tt>true</tt> if array is sorted in ascending order, false otherwise
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    /********************************************************************
     * General helper functions.
     ********************************************************************/

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Print elements of array a, in a single line
     *
     * @param a array to be printed
     */
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    /**
     * In-place sort the array in ascending order
     *
     * @param a array of Comparable to be sorted
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        // construct the heap
        for (int k = N / 2; k >= 1; k--)
            sink(a, k, N);
        // sort down
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    /********************************************************************
     * Heap helper functions.
     ********************************************************************/

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = k * 2;
            if (j < N && greater(a, j + 1, j)) j++;
            if (!greater(a, j, k)) break;
            exch(a, j, k);
            k = j;
        }
    }

    private static boolean greater(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) > 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = swap;
    }
}

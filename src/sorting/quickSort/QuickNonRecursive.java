package sorting.quickSort;

import fundamentals.bagQueueStack.stack.Stack;
import libs.StdOut;
import libs.StdRandom;

/**
 * Non-recursive version of QuickSort
 */
public class QuickNonRecursive {
    public static void main(String[] args) {
        // size of array
        int N = Integer.parseInt(args[0]);
        // Generate random Integers
        Integer[] a = randomInts(N);
        // Sort the array
        sort(a);
        // Test whether the array is sorted
        assert isSorted(a);
        // Print the array
        show(a);
    }

    public static void sort(Comparable[] a) {
        // Sort the array in ascending order
        // Shuffle the array preventing from worse case performance
        StdRandom.shuffle(a);

        // Stack keep track of sub arrays
        Stack<Integer> indices = new Stack<>();

        // Push the whole array on to stack
        indices.push(a.length - 1);
        indices.push(0);

        while (!indices.isEmpty()) {
            // Sort a[lo..hi]
            int lo = indices.pop();
            int hi = indices.pop();

            // Array is sorted
            if (hi <= lo) continue;

            // partition the array
            int j = partition(a, lo, hi);

            // push right sub array onto stack
            indices.push(hi);
            indices.push(j + 1);

            // push left sub array onto stack
            indices.push(j - 1);
            indices.push(lo);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition into a[lo..j-1], a[j], a[j+1..hi] so that
        // a[lo..j-1] <= a[j] <= a[j+1..hi]

        // left and right scanning indices
        int i = lo, j = hi + 1;
        // Partitioning element
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i > hi) break;
            while (less(v, a[--j])) ;
            if (i >= j) break;

            exch(a, i, j);
        }
        exch(a, lo, j);
        // a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
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

    public static Integer[] randomInts(int N) {
        // Generate N Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array is sorted in ascending order
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print elements, on a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

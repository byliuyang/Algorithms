package sorting.applications;

import libs.StdOut;
import libs.StdRandom;
import sorting.quickSort.Quick;

/**
 * Counting the distinct keys in array
 */
public class DistinctKeys {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);
        // Generate random array
        Integer[] a = randomInts(N);
        // Sort the array
        Quick.sort(a);
        // Assume a.length > 1
        int count = 1;
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i - 1]) != 0) count++;
        StdOut.printf("%d distinct keys in the array\n", count);
    }

    public static Integer[] randomInts(int N) {
        // Generate random Integers between 0 and N - 1
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, N);
        return a;
    }
}

package sorting.applications;

import libs.StdOut;
import libs.StdRandom;
import sorting.mergeSort.Inversions;

/**
 * Compilation: javac KendallTau.java
 * Execution: java KendallTau
 * Dependencies:
 *
 * Generate two random permutations of size N and compare their
 * Kendall tau distance (number of inversion)
 */
public class KendallTau {
    public static void main(String[] args) {
        // two random permutations of size n
        int n = Integer.parseInt(args[0]);
        int[] a = KendallTau.permutation(n);
        int[] b = KendallTau.permutation(n);

        // print initial permutations
        for (int i = 0; i < n; i++)
            StdOut.println(a[i] + " " + b[i]);
        StdOut.println();
        StdOut.println("Inversions = " + KendallTau.distance(a, b));
    }

    // Return Kendall tau distance between two permutations
    public static long distance(int[] a, int[] b) {
        if (a.length != b.length) throw new IllegalArgumentException("Array dimensions disagree");
        int n = a.length;

        int[] ainv = new int[n];
        for (int i = 0; i < n; i++)
            ainv[a[i]] = i;

        Integer[] bnew = new Integer[n];
        for (int i = 0; i < n; i++)
            bnew[i] = ainv[b[i]];

        return Inversions.count(bnew);
    }

    // return a random permutation of size n
    public static int[] permutation(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        StdRandom.shuffle(a);
        return a;
    }
}

package sorting;

import fundamentals.analysis.StopWatch;
import libs.StdOut;
import libs.StdRandom;
import sorting.elementarySorts.Insertion;
import sorting.elementarySorts.InsertionSentinel;
import sorting.elementarySorts.Selection;
import sorting.elementarySorts.Shell;
import sorting.mergeSort.*;
import sorting.priorityQueues.Heap;
import sorting.quickSort.Quick;
import sorting.quickSort.Quick3way;

/**
 * Compare two sorting algorithms
 */
public class SortCompare {
    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];

        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);

        StdOut.printf("For %d random Doubles\n      %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // Use alg to sort T random arrays of length N.

        double total = 0.0;
        for (int t = 0; t < T; t++) {
            // Perform one experiment (generate and sort an array)

            Double[] a = new Double[N];
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static double time(String alg, Double[] a) {
        StopWatch timer = new StopWatch();
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("CmpInsertion")) Insertion.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("InsertionSentinel")) InsertionSentinel.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("MergeFaster")) MergeFaster.sort(a);
        if (alg.equals("MergeImproved")) MergeImproved.sort(a);
        if (alg.equals("NaturalMerge")) NaturalMerge.sort(a);
        if (alg.equals("ThreeWayMerge")) ThreeWayMerge.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Quick3way")) Quick3way.sort(a);
        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime();
    }
}

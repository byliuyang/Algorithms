package fundamentals.analysis;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Determine the number of pairs of integers in an input file that sum to 0
 * Time complexity
 * => MergeSort(Nlog N) + Loop(N) * BinarySearch(Log N)
 * => NLog N
 */
public class TwoSumFast {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        // Count pairs that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            if (Arrays.binarySearch(a, -a[i]) > i) cnt++;
        return cnt;
    }
}
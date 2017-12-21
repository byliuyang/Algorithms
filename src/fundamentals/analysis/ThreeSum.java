package fundamentals.analysis;

import libs.In;
import libs.StdOut;

/**
 * Counts the number of triples in a file of N integers that sum to 0
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        // Counts triples that sums to 0
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0) cnt++;

        return cnt;
    }
}


package fundamentals.analysis;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Counts the number of triples in a file of N integers that sum to 0
 */
public class ThreeSumFast {
    public static void main(String[] args) {
        int[] a = In.readInts();
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        // Counts triples that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (Arrays.binarySearch(a, -a[i] - a[j]) > j) cnt++;
        return cnt;
    }
}

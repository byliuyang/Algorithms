package fundamentals.analysis;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Linear after sorting to count the pairs that sum to zero after
 * the array is sorted
 */
public class TwoSumFaster {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        int N = a.length;

        // Sort array
        Arrays.sort(a);

        int cnt = 0;
        int i = 0;
        int j = N - 1;

        // Working through the number axis
        while (i < j && i >= 0 && j < N) {
            if ((a[i] > 0 && a[j] > 0) || (a[i] < 0 && a[j] < 0))
                // Both positive or negative
                break;
            else if (a[i] + a[j] == 0) {
                cnt++;
                i++;
                j--;
            } else if (Math.abs(a[i]) > Math.abs(a[j])) i++;
            else if (Math.abs(a[i]) < Math.abs(a[j])) j--;
        }

        return cnt;
    }
}

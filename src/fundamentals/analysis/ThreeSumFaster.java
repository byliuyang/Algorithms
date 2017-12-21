package fundamentals.analysis;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Quadratic time to count the triples that sum to zero after
 * the array is sorted
 */
public class ThreeSumFaster {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        int N = a.length;

        // Sort array
        Arrays.sort(a);

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            int j = i + 1;
            int k = N - 1;

            // Working through the number axis
            while (j < k && j >= i + 1 && k < N) {
                if (a[i] > 0 && (a[j] > 0 && a[k] > 0) || (a[i] < 0 && a[j] < 0 && a[k] < 0))
                    // All positive or negative
                    break;
                else if (a[j] + a[k] == -a[i]) {
                    cnt++;
                    j++;
                    k--;
                } else if (a[j] + a[k] > -a[i])
                    // Smaller
                    k--;
                else if (a[j] + a[k] < -a[i])
                    // Bigger
                    j++;
            }
        }

        return cnt;
    }
}

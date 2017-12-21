package fundamentals.analysis;

import libs.StdOut;

import java.util.Arrays;

/**
 * Search with only addition and subtraction
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int q = Integer.parseInt(args[0]);
        //        int[] a = {1, 2, 4, 5, 8, 9, 10, 14, 11, 7, 2, -4, -9, 13, 15};
        int[] a = {-1, 2, 5, 7};
        Arrays.sort(a);

        StdOut.println(fibonacciSearch(a, q));
    }

    public static int fibonacciSearch(int[] a, int q) {

        // Size of array
        int N = a.length;

        // The n - 2 term
        int fN2 = 0;
        // The n - 1 term
        int fN1 = 1;
        // The n term
        int fN = fN1 + fN2;

        // Generate Fibonacci number just larger
        // than the size of array
        while (fN < N) {
            fN2 = fN1;
            fN1 = fN;
            fN = fN1 + fN2;
        }

        // Start
        int i = 0;

        while (fN > 1) {
            // Location to check
            fN2 = fN - fN1;

            // Prevent from out of bound
            int m = Math.min(i + fN2, N - 1);

            // Find the query
            if (q == a[m]) return m;
                // Cut lower
            else if (q > a[m]) {
                i = i + fN2;
                fN = fN1;
                fN1 = fN2;
            }
            // Cut upper
            else {
                fN = fN2;
                fN1 = fN1 - fN2;
            }
        }

        // Check the last element
        if (a[i] == q) return i;

        return -1;
    }
}

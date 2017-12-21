package sorting.priorityQueues;

import libs.StdOut;

/**********************************************************************************
 *
 * Compilation: javac CubeSum.java
 * Execution: java CubeSum n
 * Dependencies: CmpMinPQ.java Stdout.java
 *
 * Print out integers of the form a^3 + b ^ 3 in sorted order, where
 * 0 <= a <= b <= n
 *
 * % java CubeSum 10
 * 0 = 0^3 + 0^3
 * 1 = 0^3 + 1^3
 * 2 = 1^3 + 1^3
 * 8 = 0^3 + 2^3
 * 9 = 1^3 + 2^3
 * 16 = 2^3 + 2^3
 * 27 = 0^3 + 3^3
 * 28 = 1^3 + 3^3
 * ...
 * 1343 = 7^3 + 10^3
 * 1458 = 9^3 + 9^3
 * ...
 * 2000 = 10^3 + 10^3
 *
 * Remarks
 * -------
 *  - Easily extends to handle sums of the form f(a) + g(b)
 *  - Print the sum more than once if it can be obtained in
 *    more than one way
 *
 **********************************************************************************/

public class CubeSum implements Comparable<CubeSum> {
    private int sum;
    private int i;
    private int j;

    public CubeSum(int i, int j) {
        this.sum = i * i * i + j * j * j;
        this.i = i;
        this.j = j;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        MinPQ<CubeSum> pq = new MinPQ<>(N + 1);

        // Initialize priority queue
        for (int i = 0; i <= N; i++)
            pq.insert(new CubeSum(i, i));

        // find smallest sum, print it out, and update
        while (!pq.isEmpty()) {
            CubeSum s = pq.delMin();
            StdOut.println(s);

            if (s.j < N) pq.insert(new CubeSum(s.i, s.j + 1));
        }
    }

    @Override
    public int compareTo(CubeSum that) {
        if (this.sum > that.sum) return 1;
        if (this.sum < that.sum) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }
}

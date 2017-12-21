package sorting.priorityQueues;

import libs.StdOut;

/************************************************************************************
 *
 * Compilation: javac UnorderedArrayMaxPQ.java
 * Execution: java UnorderedArrayMaxPQ
 * Dependencies: StdOut.java
 *
 * Priority queue implemented with an unsorted array
 *
 * Limitations
 * -----------
 * - no array resizing
 * - no overflow or underflow check
 *
 ************************************************************************************/

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    // elements
    private Key[] pq;
    // number of elements
    private int   n;

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    /**************************************************************
     * Test routing
     **************************************************************/

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<>(10);

        pq.insert("A");
        pq.insert("B");
        pq.insert("C");
        pq.insert("D");
        pq.insert("E");

        while (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        StdOut.println();
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;
        exch(max, n - 1);
        return pq[--n];
    }

    /**************************************************************
     * Helper functions
     **************************************************************/

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public void insert(Key k) {
        pq[n++] = k;
    }

    public int size() {
        return n;
    }
}

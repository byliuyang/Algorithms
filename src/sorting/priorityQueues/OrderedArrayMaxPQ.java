package sorting.priorityQueues;

import libs.StdOut;

/************************************************************************************
 *
 * Compilation: javac OrderedArrayMaxPQ.java
 * Execution: java OrderedArrayMaxPQ
 * Dependencies: StdOut.java
 *
 * Priority queue implemented with array sorted in descending order
 *
 * Limitations
 * -----------
 *  - no array resizing
 *  - no underflow or overflow check
 *
 ************************************************************************************/

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    // elements
    private Key[] pq;
    // number of elements
    private int   n;

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    /************************************************************************************
     * Test routing
     ************************************************************************************/

    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<>(10);

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

    public void insert(Key k) {
        int i = n - 1;
        while (i >= 0 && less(k, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = k;
        n++;
    }

    /************************************************************************************
     * Helper functions
     ************************************************************************************/

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public Key delMax() {
        return pq[--n];
    }

    public int size() {
        return n;
    }
}

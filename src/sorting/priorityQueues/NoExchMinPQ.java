package sorting.priorityQueues;

import libs.StdOut;

/**********************************************************************************************
 *
 * Compilation: javac NoExchMinPQ.java
 * Execution: java NoExchMinPQ
 * Dependencies: StdOut.java
 *
 * Minimum-oriented priority queue implemented without exchange
 *
 * Limitations
 * -----------
 *  - No overflow and underflow check
 *  - No array resizing
 *
 **********************************************************************************************/

public class NoExchMinPQ<Key extends Comparable<Key>> {
    // elements
    private Key[] pq;
    // number of elements
    private int   N;

    /**
     * Create priority queue with maximum capacity
     *
     * @param capacity maximum number of element in the priority queue
     */
    public NoExchMinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    public static void main(String[] args) {
        NoExchMinPQ<String> pq = new NoExchMinPQ<>(10);

        pq.insert("F");
        pq.insert("A");
        pq.insert("C");
        pq.insert("B");
        pq.insert("D");

        while (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        StdOut.println();
    }

    /**
     * Return <tt>true</tt> when priority queue is empty, <tt>false</tt> otherwise
     *
     * @return <tt>true</tt> when priority queue is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Insert <tt>value</tt> into priority queue and restore heap properties
     *
     * @param v the value to be inserted into the priority queue
     */
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    /**********************************************************************************************
     * Heap helper functions
     **********************************************************************************************/
    private void swim(int k) {
        Key v = pq[k];

        while (k > 1 && greater(pq[k / 2], v)) {
            pq[k] = pq[k / 2];
            k = k / 2;
        }
        pq[k] = v;
    }

    /**********************************************************************************************
     * General helper functions
     **********************************************************************************************/
    private boolean greater(Key v, Key w) {
        return v.compareTo(w) > 0;
    }

    /**
     * Return and delete the minimum element in the priority queue,
     * then restore heap properties
     *
     * @return the minimum element in the priority queue
     */
    public Key delMin() {
        Key min = pq[1];
        pq[1] = pq[N];
        pq[N--] = null;
        sink(1);
        return min;
    }

    private void sink(int k) {
        Key v = pq[k];

        while (2 * k <= N) {
            int j = k * 2;
            if (j < N && greater(pq[j], pq[j + 1])) j++;
            if (!greater(v, pq[j])) break;
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = v;
    }

    /**
     * Return the number of element in the priority queue
     *
     * @return the number of element in the priority queue
     */
    public int size() {
        return N;
    }
}

package sorting.priorityQueues;

import libs.StdOut;

/**********************************************************************************************
 *
 * Compilation: javac MinMaxPQ.java
 * Execution: java MinMaxPQ
 * Dependencies: Stdout.java
 *
 * Max priority queue, accessing minimum in constant time, implemented with heap
 *
 * Limitations
 * -----------
 *  - No overflow and underflow check
 *  - No array resizing
 *
 **********************************************************************************************/

public class MinMaxPQ<Key extends Comparable<Key>> {
    // elements
    private Key[] pq;
    // number of elements
    private int   N;
    // minimum element in the priority queue
    private Key   min;

    /**
     * Create priority queue with maximum capacity
     *
     * @param capacity max number of element in priority queue
     */
    public MinMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
        min = null;
    }

    /**********************************************************************************************
     * Heap helper functions
     **********************************************************************************************/

    public static void main(String[] args) {
        MinMaxPQ<String> pq = new MinMaxPQ<>(10);

        pq.insert("F");
        pq.insert("A");
        pq.insert("C");
        pq.insert("B");
        pq.insert("D");

        while (!pq.isEmpty()) {
            StdOut.println("Max = " + pq.delMax() + ", Min = " + pq.min());
        }
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
     * Insert value into priority, update minimum value and restore heap properties
     *
     * @param v value to the inserted into priority queue
     */
    public void insert(Key v) {

        // update minimum
        if (min == null || less(v, min)) min = v;

        pq[++N] = v;
        swim(N);
    }

    /**********************************************************************************************
     * General helper functions
     **********************************************************************************************/

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    /**********************************************************************************************
     * Heap helper functions
     **********************************************************************************************/
    private void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k])) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /**
     * Return and delete the maximum value in the priority queue
     *
     * @return the maximum value in the priority queue
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);

        if (isEmpty()) min = null;
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j], pq[j + 1])) j++;
            if (!less(pq[k], pq[j])) break;
            exch(k, j);
            k = j;
        }
    }

    public Key min() {
        return min;
    }

    /**
     * Return the number of elements in the priority queue
     *
     * @return the number of elements in the priority queue
     */
    public int size() {
        return N;
    }
}

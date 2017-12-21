package sorting.priorityQueues;

import libs.StdOut;

/********************************************************************
 *
 * Compilation: javac DoubleEndedPQ.java
 * Execution: java DoubleEndedPQ
 * Dependencies: StdOut.java
 *
 * Double-ended priority queue implemented with two heaps
 *
 * Limitations:
 *  - no overflow check
 *  - no array resizing
 *
 ********************************************************************/

public class DoubleEndedPQ<Key extends Comparable<Key>> {
    // maximum heap (indices of elements)
    private int[] maxPQ;
    // maximum heap index of ith element in minimum heap
    private int[] maxIndices;
    // minimum heap (indices of elements)
    private int[] minPQ;
    // minimum heap index of ith element in maximum heap
    private int[] minIndices;
    // elements
    private Key[] keys;

    // number of elements in the queue
    private int n;

    /**
     * Create Double-ended priority queue
     *
     * @param capacity maximum number of elements in the heap
     */
    public DoubleEndedPQ(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.maxPQ = new int[capacity];
        this.minPQ = new int[capacity];
        this.maxIndices = new int[capacity];
        this.minIndices = new int[capacity];
        n = 0;
    }

    /********************************************************************
     * Test routing
     ********************************************************************/

    public static void main(String[] args) {
        DoubleEndedPQ<String> pq = new DoubleEndedPQ<>(10);

        pq.insert("C");
        pq.insert("H");
        pq.insert("Z");
        pq.insert("A");
        pq.insert("K");
        pq.insert("L");
        pq.insert("V");
        pq.insert("M");
        pq.insert("O");

        int i = 0;
        while (!pq.isEmpty()) {
            if (i % 2 == 0) StdOut.println("Remove:" + pq.delMax() + "  Max:" + pq.max() + "  Min:" + pq.min());
            else StdOut.println("Remove:" + pq.delMin() + "  Max:" + pq.max() + "  Min:" + pq.min());
            i++;
        }
    }

    /**
     * Return <tt>true</tt> if heap is empty, <tt>false</tt> otherwise
     *
     * @return <tt>true</tt> if heap is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return number of elements in the heap
     *
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * Insert a new key to heap and restore heap properties
     *
     * @param key element to be inserted
     */
    public void insert(Key key) {
        n++;
        // Add new key to heap bottom
        keys[n] = key;
        maxPQ[n] = n;
        minPQ[n] = n;
        maxIndices[n] = n;
        minIndices[n] = n;
        // restore heap property
        swim(n);
    }

    /**
     * Return and remove the maximum key in the heap
     *
     * @return the maximum key in the heap
     */
    public Key delMax() {
        // heap is empty
        if (isEmpty()) return null;
        // retrieve maximum
        Key key = keys[maxPQ[1]];
        // find maximum in min heap
        int minIndex = minIndices[1];
        // move bottom to the tops
        exchMax(1, n);
        exchMin(minIndex, n);
        // Optimize for garbage collection
        keys[maxPQ[n]] = null;
        maxPQ[n] = 0;
        minPQ[n] = 0;
        n--;
        // restore maximum heap properties
        sinkMax(1);
        // restore minimum heap properties
        if (minIndex <= n) {
            swimMin(minIndex);
            sinkMin(minIndex);
        }
        return key;
    }

    /**
     * Return and remove the minimum key in the heap
     *
     * @return the minimum key in the heap
     */
    public Key delMin() {
        // heap is empty
        if (isEmpty()) return null;
        // retrieve minimum
        Key key = keys[minPQ[1]];
        // find minimum in max heap
        int maxIndex = maxIndices[1];
        // move bottom to the tops
        exchMin(1, n);
        exchMax(maxIndex, n);
        // Optimize for garbage collection
        keys[minPQ[n]] = null;
        maxPQ[n] = 0;
        minPQ[n] = 0;
        n--;
        // restore minimum heap properties
        sinkMin(1);
        // restore maximum heap properties
        if (maxIndex <= n) {
            swimMax(maxIndex);
            sinkMax(maxIndex);
        }
        return key;
    }

    /**
     * Return the maximum key in the heap
     *
     * @return the maximum key in the heap
     */
    public Key max() {
        if (isEmpty()) return null;
        return keys[maxPQ[1]];
    }

    /**
     * Return the minimum key in the heap
     *
     * @return the minimum key in the heap
     */
    public Key min() {
        if (isEmpty()) return null;
        return keys[minPQ[1]];
    }

    /********************************************************************
     * General helper functions
     ********************************************************************/

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    private boolean greater(Key v, Key w) {
        return v.compareTo(w) > 0;
    }

    private void exchMax(int i, int j) {
        exch(maxPQ, minIndices, maxIndices, i, j);
    }

    private void exchMin(int i, int j) {
        exch(minPQ, maxIndices, minIndices, i, j);
    }

    private void exch(int[] pq, int[] indices1, int[] indices2, int i, int j) {
        // swap indices of keys
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        // swap indices
        int swapIndexJ = indices1[i];
        indices1[i] = indices1[j];
        indices1[j] = swapIndexJ;

        int swapIndexI = indices1[i];
        // swap indices
        swap = indices2[swapIndexI];
        indices2[swapIndexI] = indices2[swapIndexJ];
        indices2[swapIndexJ] = swap;
    }

    /********************************************************************
     * Heap helper functions
     ********************************************************************/

    private void swim(int k) {
        swimMax(k);
        swimMin(k);
    }

    private void swimMax(int k) {
        while (k > 1 && less(keys[maxPQ[k / 2]], keys[maxPQ[k]])) {
            exchMax(k / 2, k);
            k = k / 2;
        }
    }

    private void swimMin(int k) {
        while (k > 1 && greater(keys[minPQ[k / 2]], keys[minPQ[k]])) {
            exchMin(k / 2, k);
            k = k / 2;
        }
    }

    private void sinkMax(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(keys[maxPQ[j]], keys[maxPQ[j + 1]])) j++;
            if (!less(keys[maxPQ[k]], keys[maxPQ[j]])) break;
            exchMax(k, j);
            k = j;
        }
    }

    private void sinkMin(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(keys[minPQ[j]], keys[minPQ[j + 1]])) j++;
            if (!greater(keys[minPQ[k]], keys[minPQ[j]])) break;
            exchMin(k, j);
            k = j;
        }
    }
}
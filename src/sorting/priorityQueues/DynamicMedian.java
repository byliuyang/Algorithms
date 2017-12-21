package sorting.priorityQueues;

import libs.StdOut;

/**********************************************************************************
 *
 * Compilation: javac DynamicMedian.java
 * Execution: java DynamicMedian
 * Dependencies: StdOut.java
 *
 * A data type that supports insert in logarithmic time,
 * find the median in constant time, and delete the
 * median in logarithmic time
 *
 * Limitations
 *  - no overflow check
 *  - no array resizing
 *
 * % java DynamicMedian
 *
 * Insert:2 Median:2
 * Insert:1 Median:2
 * Insert:0 Median:1
 * Insert:5 Median:2
 * Insert:9 Median:2
 * Insert:7 Median:5
 * Insert:3 Median:3
 * Insert:4 Median:4
 * Insert:2 Median:3
 * Insert:7 Median:4
 *
 * Delete median:4
 * Delete median:3
 * Delete median:5
 * Delete median:2
 * Delete median:7
 * Delete median:2
 * Delete median:7
 * Delete median:1
 * Delete median:9
 * Delete median:0
 *
 **********************************************************************************/

public class DynamicMedian<Key extends Comparable<Key>> {
    // elements greater than or equal to median
    private Key[] minPQ;
    // elements less than or equal to median
    private Key[] maxPQ;

    // number of elements in maximum heap
    private int minPQn;
    // number of elements in minimum heap
    private int maxPQn;

    /**
     * Create median heap with given <tt>capacity/tt>
     *
     * @param capacity number of elements in median heap
     */
    public DynamicMedian(int capacity) {
        minPQ = (Key[]) new Comparable[capacity / 2];
        maxPQ = (Key[]) new Comparable[capacity / 2];
        minPQn = 0;
        maxPQn = 0;
    }

    /**********************************************************************************
     * Test routing
     **********************************************************************************/

    public static void main(String[] args) {
        DynamicMedian<Integer> dm = new DynamicMedian<>(20);
        Integer[] numbers = {2, 1, 0, 5, 9, 7, 3, 4, 2, 7};
        for (Integer v : numbers) {
            dm.insert(v);
            StdOut.println("Insert:" + v + " Median:" + dm.median());
        }
        StdOut.println();
        while (!dm.isEmpty()) StdOut.println("Delete median:" + dm.delMedian());
    }

    /**
     * Return <tt>true</tt> when median heap is empty, <tt>false</tt> otherwise
     *
     * @return <tt>true</tt> when median heap is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of elements in the heap
     *
     * @return the number of elements in the heap
     */
    public int size() {
        return maxPQn + minPQn;
    }

    /**
     * Insert value <tt>v</tt> into heap and restore heap properties
     *
     * @param v value to be inserted
     */
    public void insert(Key v) {
        // heap is empty
        if (isEmpty()) {
            insertMax(v);
            return;
        }

        Key median = median();
        // Insert either into max heap when the value is less than median or
        // into min heap when the value is greater than or equal to median
        if (less(v, median)) insertMax(v);
        else insertMin(v);

        int diff = diff();

        // When there are 1 more elements in one heap than the other,
        // move the top of that heap into the other
        if (diff > 1) insertMin(delMax());
        else if (diff < -1) insertMax(delMin());
    }

    /**
     * Return the median of elements
     *
     * @return the median of elements
     */
    public Key median() {
        int diff = diff();

        // When there are 1 more elements in one heap than the other,
        // return the top of that heap as median
        if (diff == 1) return maxPQ[1];
        else if (diff == -1) return minPQ[1];
        else if (diff == 0 && !isEmpty()) {
            // Return the maximum top of the two heap when they have the same
            // number of elements
            if (greater(maxPQ[1], minPQ[1])) return maxPQ[1];
            else return minPQ[1];
        }
        // No median
        return null;
    }

    /**
     * Return and delete the median in the heap
     *
     * @return the median in the heap
     */
    public Key delMedian() {

        int diff = diff();

        // When there are 1 more elements in one heap than the other,
        // delete and return the top of that heap as median
        if (diff == 1) return delMax();
        else if (diff == -1) return delMin();
        else if (diff == 0 && !isEmpty()) {
            // Delete and return the maximum top of the two heap when they have the same
            // number of elements
            if (greater(maxPQ[1], minPQ[1])) return delMax();
            else return delMin();
        }
        // No median
        return null;
    }

    /**********************************************************************************
     * Heap helper functions
     **********************************************************************************/

    private void insertMax(Key v) {
        maxPQ[++maxPQn] = v;
        swimMax(maxPQn);
    }

    private void insertMin(Key v) {
        minPQ[++minPQn] = v;
        swimMin(minPQn);
    }

    private Key delMax() {
        Key v = maxPQ[1];
        exch(maxPQ, 1, maxPQn--);
        sinkMax(1);
        maxPQ[maxPQn + 1] = null;
        return v;
    }

    private Key delMin() {
        Key v = minPQ[1];
        exch(minPQ, 1, minPQn--);
        sinkMin(1);
        minPQ[minPQn + 1] = null;
        return v;
    }

    private void swimMax(int k) {
        while (k > 1 && less(maxPQ[k / 2], maxPQ[k])) {
            exch(maxPQ, k / 2, k);
            k = k / 2;
        }
    }

    private void swimMin(int k) {
        while (k > 1 && greater(minPQ[k / 2], minPQ[k])) {
            exch(minPQ, k / 2, k);
            k = k / 2;
        }
    }

    private void sinkMax(int k) {
        while (2 * k <= maxPQn) {
            int j = k * 2;
            if (j < maxPQn && less(maxPQ[j], maxPQ[j + 1])) j++;
            if (!less(maxPQ[k], maxPQ[j])) break;
            exch(maxPQ, k, j);
            k = j;
        }
    }

    private void sinkMin(int k) {
        while (2 * k <= minPQn) {
            int j = k * 2;
            if (j < minPQn && greater(minPQ[j], minPQ[j + 1])) j++;
            if (!greater(minPQ[k], minPQ[j])) break;
            exch(minPQ, k, j);
            k = j;
        }
    }

    /**********************************************************************************
     * General helper functions
     **********************************************************************************/

    private int diff() {
        return maxPQn - minPQn;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    private boolean greater(Key v, Key w) {
        return v.compareTo(w) > 0;
    }

    private void exch(Key[] heap, int i, int j) {
        Key swap = heap[i];
        heap[i] = heap[j];
        heap[j] = swap;
    }
}

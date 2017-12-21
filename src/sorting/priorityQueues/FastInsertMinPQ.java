package sorting.priorityQueues;

import libs.StdOut;
import libs.StdRandom;

/****************************************************************************************
 *
 * Compilation: javac FastInsertMinPQ.java
 * Execution: java FastInsertMinPQ
 * Dependencies: StdOut.java StdRandom.java
 *
 * Min heap priority queue uses binary search on parent
 * pointers to find the ancestor in swim
 *
 * Insert uses ~ log log N compares and delete the minimum
 * uses ~2 log N compares
 *
 * Limitations
 * -----------
 * - no overflow check
 * - no array resizing
 *
 ****************************************************************************************/

public class FastInsertMinPQ<Key extends Comparable<Key>> {
    // Heap ordered complete binary tree
    private Key[] pq;
    // number of elements
    private int   N;

    /**
     * Create min heap
     *
     * @param maxN max number of elements in the heap
     */
    public FastInsertMinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /****************************************************************************************
     * Test routing
     ****************************************************************************************/

    public static void main(String[] args) {
        FastInsertMinPQ<Integer> pq = new FastInsertMinPQ<>(101);
        // Generate array of random numbers
        for (int i = 0; i < 100; i++)
            pq.insert(StdRandom.uniform(0, 100));
        Integer[] a = new Integer[100];
        // Perform heap sort in ascending order
        for (int i = 0; i < 100; i++)
            a[i] = pq.delMin();
        // Test whether the numbers are sorted
        if (isSorted(a)) StdOut.println("Numbers sorted");
        else StdOut.println("Numbers not sorted");
    }

    /**
     * Insert new element into heap and restore heap properties
     *
     * @param v element to be inserted
     */
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    private void swim(int k) {
        if (k == 1) return;
        Key v = pq[N];
        int m = binarySearch(k);
        if (m > -1) {
            for (int p = k; p > m; p = p / 2) pq[p] = pq[p / 2];
            pq[m] = v;
        }
    }

    /**
     * Return the correct position to insert the new item
     *
     * @param k heap bottom to search from
     *
     * @return the correct position to insert the new item
     */
    private int binarySearch(int k) {
        // calculate the number of parents of heap bottom
        int numParent = numParents(k);
        // Start binary search between heap top and the first parent
        int lo = 0;
        int hi = numParent;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            // Convert binary division to array location
            int p = findParent(mid, numParent, k);
            if (greater(p, k)) hi = mid - 1;
            else if (less(p, k)) lo = mid + 1;
            else return p;
        }
        // lo <= k / 2
        lo = Math.min(findParent(lo, numParent, k), k / 2);
        // hi >= 1
        hi = Math.max(findParent(hi, numParent, k), 1);
        // less than hi
        if (greater(hi, k)) return hi;
        // less than lo
        if (greater(lo, k)) return lo;
        // not reaching
        return -1;
    }

    /**
     * Find parent located at <tt>p</tt> in the heap
     *
     * @param p  parent location
     * @param hi first parent
     * @param k  heap bottom
     *
     * @return
     */
    private int findParent(int p, int hi, int k) {
        for (int i = p; i < hi; i++)
            k = k / 2;
        return k;
    }

    /**
     * Return the number of parents of <tt>k</tt>th element
     *
     * @param k heap bottom
     *
     * @return Return the number of parents of <tt>k</tt> th element
     */
    private int numParents(int k) {
        int count = 0;
        int j = k;
        while (j > 1) {
            count++;
            j = j / 2;
        }
        return count;
    }

    /****************************************************************************************
     * General helper functions
     ****************************************************************************************/

    private boolean greater(int v, int w) {
        return pq[v].compareTo(pq[w]) > 0;
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    /**
     * Return and delete the maximum in the heap
     *
     * @return the maximum in the heap
     */
    public Key delMin() {
        // Retrieve minimum key
        Key min = pq[1];
        // Exchange with last key
        exch(1, N--);
        // Prevent from loitering
        pq[N + 1] = null;
        // Restore heap property
        sink(1);
        return min;
    }

    /****************************************************************************************
     * Heap helper functions
     ****************************************************************************************/

    private void sink(int k) {
        while (2 * k <= N) {
            int j = k * 2;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /****************************************************************************************
     * Test helper functions
     ****************************************************************************************/

    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Return <tt>true</tt> when heap is empty, false otherwise <tt>false</tt>
     *
     * @return <tt>true</tt> when heap is empty, false otherwise <tt>false</tt>
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Return the number of element in the heap
     *
     * @return the number of element in the heap
     */
    public int size() {
        return N;
    }
}

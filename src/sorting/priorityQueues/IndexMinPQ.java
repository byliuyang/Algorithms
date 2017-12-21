package sorting.priorityQueues;

import java.util.NoSuchElementException;

/**********************************************************************
 *
 * Compilation: javac IndexMinPQ.java
 * Execution: java IndexMinPQ
 * Dependencies: StdOut.java
 *
 * Minimum-oriented indexed PQ implementation using a binary heap
 *
 **********************************************************************/

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int   maxN; // maximum number of elements on PQ
    private int   n; // number of elements on PQ
    private int[] pq; // binary heap using 1-based indexing
    private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys; // keys[i] = priority of i

    /**
     * Initialize an empty indexed priority queue with indices between <tt>0</tt>
     * and <tt>maxN - 1</tt>.
     *
     * @param maxN the keys on this priority queue are index from <tt>0</tt>
     *             <tt>maxN - 1</tt>
     *
     * @throws IllegalArgumentException if <tt>maxN</tt>&lt;<tt>0</tt>
     */
    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return <tt>true</tt> if this priority queue is empty;
     * <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Associate key with index <tt>i</tt>
     *
     * @param i   an index
     * @param key the key to associate with index
     *
     * @throws IndexOutOfBoundsException unless 0 &le; <tt>i</tt> &lt; <tt>maxN</tt>
     * @throws IllegalArgumentException  if there is already is an item associated
     *                                   with index <tt>i</tt>
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Is <tt>i</tt> an index on this priority queue?
     *
     * @param i an index
     *
     * @return <tt>true</tt> if <tt>i</tt> is an index on this priority queue;
     * <tt>false</tt> otherwise
     *
     * @throws IndexOutOfBoundsException unless 0 &le; <tt>i</tt> &lt; <tt>maxN</tt>
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    /*************************************************************
     * Heap helper functions.
     *************************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /*************************************************************
     * General helper functions.
     *************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key.
     *
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     *
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key min() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     *
     * @return a index associated with a minimum key
     *
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        // delete
        qp[min] = -1;
        // to help with garbage collection
        keys[min] = null;
        // not needed
        pq[n + 1] = -1;
        return min;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /**
     * Change the key associated with index <tt>i</tt> to the specific value.
     *
     * @param i   the index of the key to change
     * @param key change the key associated with index <tt>i</tt> to this key
     *
     * @throws IndexOutOfBoundsException unless 0 &le; <tt>i</tt> &lt; <tt>maxN</tt>
     * @throws NoSuchElementException    no key is associated with index <tt>i</tt>
     */
    public void change(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index <tt>i</tt>.
     *
     * @param i the index of the key to remove
     *
     * @throws IndexOutOfBoundsException unless 0 &le; <tt>i</tt> &lt; <tt>maxN</tt>
     * @throws NoSuchElementException    no key is associated with index <tt>i</tt>
     */
    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = pq[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }
}

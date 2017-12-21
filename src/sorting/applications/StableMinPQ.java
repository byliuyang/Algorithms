package sorting.applications;


import libs.StdOut;

/*************************************************************************************
 *
 * Compilation: javac StableMinPQ.java
 * Execution: java StableMinPQ
 * Dependencies:
 *
 * Generic min priority queue implementation with binary heap.
 * The min() and delMin() methods always return the minimum key
 * the as inserted least recently when there are two or more.
 *
 * % java StableMinPQ
 *
 *************************************************************************************/

public class StableMinPQ<Key extends Comparable<Key>> {
    // store elements at indices 1 to N
    private Key[]  pq;
    // timestamp
    private long[] time;
    // number of elements on priority queue
    private int    n;
    // timestamp for when item was inserted
    private long timestamp = 1;

    /**
     * Create an empty priority queue
     */
    public StableMinPQ() {
        this(1);
    }

    /**
     * Create empty priority queue with initial capacity
     */
    public StableMinPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        time = new long[initCapacity + 1];
        n = 0;
    }

    /*************************************************************************************
     * Test routing
     *************************************************************************************/

    public static void main(String[] args) {
        StableMinPQ<Tuple> pq = new StableMinPQ<>();

        String text = "it was the best of times it was the worst of times it was the age of wisdom it was the " +
                      "age of foolishness it was the epoch belief it was the epoch of incredulity it was the " +
                      "season of light it was the season of darkness it was the spring of hope it was the winter of "
                      + "despair";

        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++)
            pq.insert(new Tuple(strings[i], i));

        while (!pq.isEmpty()) StdOut.println(pq.delMin() + " ");

    }

    /**
     * Add a new key to the priority queue
     */
    public void insert(Key v) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(pq.length * 2);

        // add x
        n++;
        pq[n] = v;
        time[n] = ++timestamp;
        swim(n);
    }

    /*************************************************************************************
     * Heap helper functions
     *************************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /*************************************************************************************
     * General helper functions
     *************************************************************************************/

    private boolean greater(int v, int w) {
        int cmp = pq[v].compareTo(pq[w]);
        if (cmp > 0) return true;
        else if (cmp < 0) return false;
        return time[v] > time[w];
    }

    private void exch(int i, int j) {
        Key swapKey = pq[i];
        pq[i] = pq[j];
        pq[j] = swapKey;
        long swapTime = time[i];
        time[i] = time[j];
        time[j] = swapTime;

    }

    /**
     * Change the size of heap array
     */
    private void resize(int capacity) {
        Key[] tempPQ = (Key[]) new Comparable[capacity];
        long[] tempTime = new long[capacity];
        for (int i = 1; i <= n; i++)
            tempPQ[i] = pq[i];
        for (int i = 1; i <= n; i++)
            tempTime[i] = time[i];
        pq = tempPQ;
        time = tempTime;
    }

    /**
     * Is the priority queue empty?
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Delete and return the smallest key on the priority queue
     */
    public Key delMin() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        time[n + 1] = 0;
        if (n > 0 && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return min;
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = k * 2;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /**
     * Return number of elements in the priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Return the smallest key on the priority queue
     */
    public Key min() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    private static final class Tuple implements Comparable<Tuple> {
        private String name;
        private int    id;

        public Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.name.compareTo(that.name);
        }

        @Override
        public String toString() {
            return name + " " + id;
        }
    }
}

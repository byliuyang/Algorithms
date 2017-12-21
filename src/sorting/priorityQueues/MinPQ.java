package sorting.priorityQueues;

import libs.StdOut;

/**
 * Min heap priority queue
 */
public class MinPQ<Key extends Comparable<Key>> {
    // Heap ordered complete binary tree
    private Key[] pq;
    private int   N;

    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<>(10);

        pq.insert("F");
        pq.insert("A");
        pq.insert("C");
        pq.insert("B");
        pq.insert("D");

        while (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        StdOut.println();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private boolean greater(int v, int w) {
        return pq[v].compareTo(pq[w]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

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

    private void sink(int k) {
        while (2 * k <= N) {
            int j = k * 2;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public int size() {
        return N;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > N) return true;
        int left = k * 2;
        int right = k * 2 + 1;

        if (greater(k, left)) return false;
        if (greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }
}

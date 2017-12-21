package sorting.priorityQueues;

import libs.StdOut;

/**
 * Heap priority queue
 */
public class MaxPQ<Key extends Comparable<Key>> {
    // Heap-ordered complete binary tree
    // in a[1..N] with pq[0] unused
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>(10);

        pq.insert("F");
        pq.insert("A");
        pq.insert("C");
        pq.insert("B");
        pq.insert("D");

        while (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        StdOut.println();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key v) {
        if (N + 1 == pq.length) resize(N * 2);
        pq[++N] = v;
        swim(N);
    }

    private void resize(int n) {
        Key[] tmp = (Key[]) new Comparable[n + 1];
        for (int i = 1; i <= N; i++)
            tmp[i] = pq[i];

        pq = tmp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    public Key delMax() {
        // Retrieve max key from top
        Key max = pq[1];
        // Exchange with last item
        exch(1, N--);
        // Avoid loitering
        pq[N + 1] = null;
        // Restore heap property
        sink(1);

        if (N + 1 == pq.length / 4) resize(N * 2);
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public int size() {
        return N;
    }
}

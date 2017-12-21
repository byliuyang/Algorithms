package sorting.applications;

import fundamentals.bagQueueStack.stack.Stack;
import libs.StdIn;
import libs.StdOut;

import java.util.Comparator;

/**
 * Min heap priority queue
 */
public class CmpMinPQ<Key> {
    // Heap ordered complete binary tree
    private Key[]           pq;
    private int             N;
    private Comparator<Key> c;

    public CmpMinPQ(int maxN, Comparator<Key> c) {
        pq = (Key[]) new Object[maxN + 1];
        this.c = c;
    }

    public static void main(String[] args) {

        // Print the top M lines in the input stream.
        int M = Integer.parseInt(args[0]);

        CmpMinPQ<CmpTransaction> pq = new CmpMinPQ<>(M + 1, new CmpTransaction.WhoOrder());

        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on PQ
            pq.insert(new CmpTransaction(StdIn.readLine()));
            if (pq.size() > M)
                // Remove minimum if M+1 entries on the PQ
                pq.delMin();
        }
        // Top M entries are on the PQ
        Stack<CmpTransaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (CmpTransaction t : stack) StdOut.println(t);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
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
        return c.compare(pq[v], pq[w]) > 0;
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
}

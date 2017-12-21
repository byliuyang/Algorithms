package sorting.mergeSort;

import fundamentals.bagQueueStack.queue.Queue;
import libs.In;
import libs.StdOut;

/**
 * Merge two sorted queue
 */
public class QueueMerge {

    public static void main(String[] args) {

        String[] a = In.readStrings();

        // Create N queue, each contains one item
        Queue<Queue<Comparable>> q = new Queue<>();

        for (String s : a) {
            Queue<Comparable> t = new Queue<>();
            t.enqueue(s);
            q.enqueue(t);
        }
        // Merge two queues
        sort(q);

        // Show queue
        show(q);
    }

    public static void sort(Queue<Queue<Comparable>> a) {
        // Merge the queue until it contains only one queue
        while (a.size() > 1) {
            Queue<Comparable> f = a.dequeue();
            Queue<Comparable> s = a.dequeue();
            a.enqueue(merge(f, s));
        }
    }

    public static Queue<Comparable> merge(Queue<Comparable> f, Queue<Comparable> s) {
        // Size of the first queue
        int fN = f.size();
        // Size of the second queue
        int sN = s.size();
        // Size of resultant queue
        int N = fN + sN;

        // Two array holding the content of the queue
        Comparable[] fA = new Comparable[fN];
        Comparable[] sA = new Comparable[sN];

        // Copy content of queue into arrays
        for (int i = 0; i < fN; i++) {
            fA[i] = f.dequeue();
            f.enqueue(fA[i]);
        }

        for (int i = 0; i < sN; i++) {
            sA[i] = s.dequeue();
            s.enqueue(sA[i]);
        }

        // Resultant queue
        Queue<Comparable> newQueue = new Queue<>();

        // Merge two queue together
        int i = 0, j = 0;
        for (int k = 0; k < N; k++)
            if (i >= fN) newQueue.enqueue(sA[j++]);
            else if (j >= sN) newQueue.enqueue(fA[i++]);
            else if (less(fA[i], sA[j])) newQueue.enqueue(fA[i++]);
            else newQueue.enqueue(sA[j++]);

        return newQueue;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Queue<Queue<Comparable>> q) {
        // Get the only child queue
        Queue<Comparable> c = q.dequeue();

        // Print the content of queue, in a line
        for (Comparable t : c) {
            StdOut.print(t + " ");
        }
        StdOut.println();

        q.enqueue(c);
    }
}

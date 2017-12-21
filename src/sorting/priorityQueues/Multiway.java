package sorting.priorityQueues;

import libs.In;
import libs.StdOut;

/**
 * Multiway merge priority queue client
 */
public class Multiway {
    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }

    public static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);

        for (int i = 0; i < N; i++)
            pq.insert(i, streams[i].readString());

        while (!pq.isEmpty()) {
            StdOut.print(pq.min() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty()) pq.insert(i, streams[i].readString());
        }
        StdOut.println();
    }
}

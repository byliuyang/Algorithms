package fundamentals.bagQueueStack.queue;

import libs.In;

public class ReadInts {
    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty()) q.enqueue(in.readInt());
        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = q.dequeue();
        return a;
    }
}

package fundamentals.bagQueueStack.queue;

import libs.StdOut;

/**
 * Created by harryliu on 7/3/16.
 */
public class Josephus {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++)
                queue.enqueue(queue.dequeue());
            StdOut.print(queue.dequeue() + " ");
        }
    }
}

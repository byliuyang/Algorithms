package fundamentals.bagQueueStack.queue;

import libs.StdIn;
import libs.StdOut;

/**
 * Implement queue with array resizing
 */
public class ResizingArrayQueueOfStrings {
    private String[] a    = new String[1];
    private int      N    = 0;
    private int      head = 0;

    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        ResizingArrayQueueOfStrings s;
        s = new ResizingArrayQueueOfStrings();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.enqueue(item);
            else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + ") left on queue");
    }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public void enqueue(String item) {
        int size = a.length - head;
        if (N == size) resize(size * 2);
        a[head + N++] = item;
    }

    private void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[head + i];
        a = temp;
        head = 0;
    }

    public String dequeue() {
        String item = a[head++];
        N--;

        int size = a.length - head;
        if (N > 0 && N == size / 4) resize(size / 2);
        return item;
    }
}

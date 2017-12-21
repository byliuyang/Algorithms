package fundamentals.bagQueueStack.queue;

import libs.StdIn;
import libs.StdOut;

import java.util.Iterator;

/**
 * Created by harryliu on 7/3/16.
 */
public class RandomQueue<Item> implements Iterable<Item> {
    private Item[] a    = (Item[]) new Object[1];
    private int    N    = 0;
    private int    head = 0;

    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        RandomQueue<String> s;
        s = new RandomQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.enqueue(item);
            else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }

        StdOut.println("(" + s.size() + ") left on queue");

        StdOut.println();

        StdOut.println("Iteration:");

        for (String item : s) {
            StdOut.print(item + " ");
        }
    }

    public void enqueue(Item item) {
        int size = a.length - head;
        if (N == size) resize(size * 2);
        a[head + N++] = item;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[head + i];
        a = temp;
        head = 0;
    }

    public boolean isEmpty() { return N == 0; }

    public Item dequeue() {
        // Swap when more than one card in the queue
        if (N > 1) randomizeSwap();

        Item item = a[head++];
        N--;

        int size = a.length - head;
        if (N > 0 && N == size / 4) resize(size / 2);
        return item;
    }

    private void randomizeSwap() {
        // Swap item at random index between 'head + 1' and 'N - 1'
        int index = head + randomInt(1, N - 1);
        Item temp = a[index];
        a[index] = a[head];
        a[head] = temp;
    }

    private int randomInt(int min, int max) {
        // generate random number between min and max
        return min + (int) (Math.random() * (max - min + 1));
    }

    public int size() { return N; }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] rest    = (Item[]) new Object[N];
        private int    current = 0;
        private int    size    = N;

        RandomIterator() {
            for (int i = 0; i < N; i++)
                rest[i] = a[head + i];
        }

        @Override
        public boolean hasNext() {
            return current < rest.length;
        }

        @Override
        public Item next() {
            if (size > 1) randomizeSwap();
            size--;
            return rest[current++];
        }

        private void randomizeSwap() {
            // Swap item at random index between 'current + 1' and 'size - 1'
            int index = current + randomInt(1, size - 1);
            Item temp = rest[index];
            rest[index] = rest[current];
            rest[current] = temp;
        }
    }
}

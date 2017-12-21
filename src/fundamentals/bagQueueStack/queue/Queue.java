package fundamentals.bagQueueStack.queue;

import libs.StdIn;
import libs.StdOut;

import java.util.Iterator;

/**
 * FIFO queue
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first; // link to least recently added node
    private Node last;  // link to most recently added node
    private int  N;

    public Queue() {}

    public Queue(Queue<Item> q) {
        // Copy a queue
        while (!q.isEmpty()) {
            Item item = q.dequeue();
            enqueue(item);
            q.enqueue(item);
        }
    }

    public boolean isEmpty() { return first == null; }

    public Item dequeue() {
        // Remove item from the beginning of the list
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    public void enqueue(Item item) {
        // Add item to the end of the list
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        Queue<String> s;
        s = new Queue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.enqueue(item);
            else if (!s.isEmpty()) StdOut.print(s.dequeue() + " ");
        }
        StdOut.println("(" + s.size() + ") left on queue");
    }

    public int size() { return N; }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        // nested class to define node
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

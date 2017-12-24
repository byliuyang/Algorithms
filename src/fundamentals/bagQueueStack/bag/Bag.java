package fundamentals.bagQueueStack.bag;

import java.util.Iterator;

/**
 * Bag
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first; // first node in the list
    private int  N;     // number of items

    public boolean isEmpty() { return first == null; }

    public int size() { return N; }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class Node {
        // nested class to define node
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;
        public ListIterator(Node first) {
            current = first;
        }

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

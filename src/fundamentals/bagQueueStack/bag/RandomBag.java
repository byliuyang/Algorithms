package fundamentals.bagQueueStack.bag;

import libs.StdOut;
import libs.StdRandom;

import java.util.Iterator;

/**
 * Stores a collection of items in random order
 */
public class RandomBag<Item> implements Iterable<Item> {
    private Node first; // list of items
    private int  N;       // number of items in the bag

    /**
     * Create an empty random bag
     */
    public RandomBag() {

    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>();

        for (int i = 0; i < 10; i++)
            bag.add(i);

        for (Integer item : bag)
            StdOut.print(item + " ");

    }

    /**
     * @param item add a item
     */
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * @return is the bag empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return number of items in the bag
     */
    public int size() {
        return N;
    }

    @Override
    public Iterator iterator() {
        return new RandomIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class RandomIterator implements Iterator<Item> {
        int    size  = N;
        Item[] items = (Item[]) new Object[N]; // items in the bag

        public RandomIterator() {
            int i = 0;
            for (Node current = first; current != null; current = current.next) {
                items[i] = current.item;
                i++;
            }

            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public Item next() {
            return items[--size];
        }
    }
}

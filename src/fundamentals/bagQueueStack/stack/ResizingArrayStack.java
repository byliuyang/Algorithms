package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

import java.util.Iterator;

/**
 * Push down stack ( resizing array implementation )
 */
public class ResizingArrayStack<Item> implements Iterable {
    private Item[] a = (Item[]) new Object[1];  // Stack items
    private int    N = 0;                       // Number of Items

    public static void main(String[] args) {
        ResizingArrayStack<String> s;
        s = new ResizingArrayStack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + ") left on stack");
    }

    public void push(Item item) {
        // Add item to top of the stack
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    private void resize(int max) {

        // Move stack to a new array of size max

        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < N; i++)
            temp[i] = a[i];

        a = temp;
    }

    public boolean isEmpty() { return N == 0; }

    public Item pop() {
        // Remove item from top of the stack
        Item item = a[--N]; // Avoid loitering
        a[N] = null;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    public int size() { return N; }

    @Override
    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        // Support LIfO iteration
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}

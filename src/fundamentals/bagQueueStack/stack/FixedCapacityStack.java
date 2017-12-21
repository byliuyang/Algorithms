package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * An abstract data type for fixed-capacity generic stack
 */
public class FixedCapacityStack<Item> {
    Item[] a;
    int    N;

    /**
     * Create empty stack of capacity cap
     *
     * @param cap
     */
    FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> s;
        s = new FixedCapacityStack<>(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + ") left in stack");
    }

    /**
     * Add an item
     */
    public void push(Item item) {
        a[N++] = item;
    }

    /**
     * is the stack empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Remove the most recently added item
     */
    public Item pop() {
        return a[--N];
    }

    /**
     * number of items on the stack
     */
    public int size() {
        return N;
    }
}

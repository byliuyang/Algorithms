package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * An abstract data type for a fixed-capacity stack of strings
 */
public class FixedCapacityStackOfStrings {

    private String[] a; // stack entry
    private int      N; // size

    /**
     * Create an empty stack of capacity cap
     */
    FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings s;
        s = new FixedCapacityStackOfStrings(100);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + ") left on stack");
    }

    /**
     * Add a string
     *
     * @param item
     */
    public void push(String item) {
        a[N++] = item;
    }

    /**
     * is the stack empty
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * remove the most recently added string
     *
     * @return
     */
    public String pop() {
        return a[--N];
    }

    /**
     * number of string on the stack
     *
     * @return
     */
    public int size() {
        return N;
    }

    public boolean isFull() { return N == a.length; }
}

package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

import java.util.Iterator;

/**
 * Pushdown stack (linked-list implementation)
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;  // Top of stack (most recently added one)
    private int  N;      // number of items

    public Stack() {}

    public Stack(Stack<Item> s) {
        // Copy stack
        Stack<Item> temp = new Stack<>();
        while (!s.isEmpty()) temp.push(s.pop());

        while (!temp.isEmpty()) {
            Item item = temp.pop();
            s.push(item);
            push(item);
        }
    }

    public boolean isEmpty() { return first == null; }

    public void push(Item item) {
        // Add item to top of the stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        // Remove item from top of stack
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public static void main(String[] args) {
        // Create a stack and push/pop strings as directed in StdIn.
        Stack<String> s = new Stack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + ") left on stack");
    }

    public int size() { return N; }

    /**
     * @return the most recently added item
     */
    public Item peek() {
        Item item = first.item;
        return item;
    }

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
        public boolean hasNext() { return current != null; }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

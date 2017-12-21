package fundamentals.bagQueueStack.linkedList;

import libs.StdIn;
import libs.StdOut;

/**
 * Read in a sequence of characters from standard input and
 * maintain the characters in a linked list with no duplicates.
 */
public class MoveToFront<Item> {
    private Node first = null;

    public static void main(String[] args) {

        MoveToFront<String> mtf = new MoveToFront<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            mtf.moveToFirst(item);
        }

        mtf.displayList();
    }

    public void displayList() {
        for (Node i = first; i != null; i = i.next)
            StdOut.print(i.item + " ");
    }

    public void moveToFirst(Item item) {
        Node target = first;
        Node last = null;

        while (target != null && !target.item.equals(item)) {
            // Search the list for target item
            last = target;
            target = target.next;
        }

        if (last != null && target != null) {
            // Find target node in middle
            // Remove target node
            last.next = target.next;

            // Append target node to front
            target.next = first;
            first = target;
        } else if (target == null) {
            // Append new item to front
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
    }

    private class Node {
        Item item;
        Node next;
    }
}

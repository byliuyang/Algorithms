package sorting.priorityQueues;

import libs.StdOut;

/************************************************************************************
 *
 * Compilation: javac OrderedLinkedListMaxPQ.java
 * Execution: java OrderedLinkedListMaxPQ
 * Dependencies: StdOut.java
 *
 * Priority queue implemented with ordered linked list
 *
 ************************************************************************************/

public class OrderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    // elements
    private Node head;
    // number of elements
    private int  n;

    public OrderedLinkedListMaxPQ() {
        head = null;
        n = 0;
    }

    /************************************************************************************
     * Test routing
     ************************************************************************************/
    public static void main(String[] args) {
        UnorderedLinkedListMaxPQ<String> pq = new UnorderedLinkedListMaxPQ<>();

        pq.insert("A");
        pq.insert("B");
        pq.insert("C");
        pq.insert("F");
        pq.insert("D");
        pq.insert("E");
        pq.insert("G");

        while (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        StdOut.println();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }

    public void insert(Key key) {
        // List is empty
        n++;

        if (head == null) {
            head = new Node(key);
            return;
        }


        Node currLast = null;
        Node curr = head;

        while (curr != null && less(key, curr.item)) {
            currLast = curr;
            curr = curr.next;
        }

        if (currLast == null) {
            Node oldHead = head;
            head = new Node(key);
            head.next = oldHead;
        } else {
            currLast.next = new Node(key);
            currLast.next.next = curr;
        }
    }

    /************************************************************************************
     * Helper functions
     ************************************************************************************/

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public Key delMax() {
        if (head == null) return null;

        // Delete maximum from the list
        Key key = head.item;
        head = head.next;
        n--;
        return key;
    }

    private class Node {
        Key  item;
        Node next;

        public Node(Key key) {
            item = key;
        }
    }
}

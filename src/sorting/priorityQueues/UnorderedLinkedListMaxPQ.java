package sorting.priorityQueues;

import libs.StdOut;

/************************************************************************************
 *
 * Compilation: javac UnorderedLinkedListMaxPQ.java
 * Execution: java UnorderedLinkedListMaxPQ
 * Dependencies: StdOut.java
 *
 * Priority queue implemented with unordered linked list
 *
 ************************************************************************************/

public class UnorderedLinkedListMaxPQ<Key extends Comparable<Key>> {
    // elements
    private Node head;
    // number of elements
    private int  n;

    public UnorderedLinkedListMaxPQ() {
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

    public void insert(Key key) {
        Node oldHead = head;
        head = new Node(key);
        head.next = oldHead;
        n++;
    }

    public Key delMax() {
        // List is empty
        if (head == null) return null;

        Node currLast = head;
        Node maxLast = null;
        Key max = head.item;

        // Find the node with maximum item
        for (Node curr = head.next; curr != null; curr = curr.next) {
            if (less(max, curr.item)) {
                max = curr.item;
                maxLast = currLast;
            }
            currLast = curr;
        }

        // Delete maximum from the list
        if (maxLast != null) maxLast.next = maxLast.next.next;
        else head = head.next;

        n--;
        return max;
    }

    /************************************************************************************
     * Helper functions
     ************************************************************************************/

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public int size() {
        return n;
    }

    private class Node {
        Key  item;
        Node next;

        public Node(Key key) {
            item = key;
        }
    }
}

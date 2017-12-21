package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * A natural mergesort for linked lists
 */
public class LinkedListMerge {
    public static void main(String[] args) {

        // number of elements in linked list
        int N = Integer.parseInt(args[0]);

        // Generate random list of items
        Node head = randomIntegers(N);

        // Sort the array
        head = sort(head);

        // Check whether list is sorted
        assert isSorted(head);

        // Print the list
        show(head);
    }

    public static Node randomIntegers(int N) {
        Node head = null;

        for (int i = 0; i < N; i++) {
            // Save old head
            Node oldHead = head;

            // Create a new node
            head = new Node();
            head.item = StdRandom.uniform(0, N);

            // Append node to the front
            head.next = oldHead;
        }

        return head;
    }

    public static Node sort(Node head) {

        Node mid, hi;

        while (true) {

            // Find the first sorted block
            mid = findSortedBlockFrom(head);

            // The listed is sorted
            if (mid.next == null) return head;

            // Find the second sorted block
            hi = findSortedBlockFrom(mid.next);

            // Merge two sorted array
            head = merge(head, mid, hi);
        }
    }

    private static Node findSortedBlockFrom(Node head) {

        // No block when list is empty
        if (head == null) return null;
        Node curr = head;

        // traverse the list and find the end
        // of sorted item starting from head
        while (curr.next != null && !less(curr.next.item, curr.item)) curr = curr.next;

        return curr;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static Node merge(Node head, Node mid, Node hi) {
        Node first = head; // head of first list
        Node second = mid.next; // head of second list

        // tail of resultant list
        Node curr = null;

        while (true) {
            if (first == mid.next) {
                // append the rest of the second list to the
                // end of current list when first list exhausted
                curr.next = second;
                return head;
            } else if (second == hi.next) {
                // append the rest of the first list to the
                // end of current list when second list exhausted
                Node temp = hi.next;
                curr.next = first;
                mid.next = temp;
                return head;
            } else if (less(first.item, second.item)) {
                // append the head of the first list to the end of current list
                // when the head of the first list is smaller than the head
                // of the second list

                if (curr == null) {
                    // make the head of the first list the head
                    // of current list
                    curr = first;
                    head = curr;
                } else {
                    // append the head of the first list to
                    // the end of current list
                    curr.next = first;
                    curr = curr.next;
                }

                // take the head off from first list
                first = first.next;
            } else {
                // append the head of the second list to end of current list
                // when the head of the second list is smaller or equal to the
                // head of the first list
                if (curr == null) {
                    // make the head of second list the head
                    // of current list
                    curr = second;
                    head = curr;
                } else {
                    // append the head of second list to
                    // the end of current list
                    curr.next = second;
                    curr = curr.next;
                }

                // take  the head off from second list
                second = second.next;
            }
        }
    }

    public static boolean isSorted(Node head) {

        // sorted when list is empty
        if (head == null) return false;

        // traverse the list and return true when its ascending order
        for (Node curr = head; head.next != null; curr = curr.next)
            if (less(curr.next.item, curr.item)) return false;

        return true;
    }

    public static void show(Node head) {
        // Print list items, in a line
        for (Node current = head; current != null; current = current.next) {
            StdOut.print(current.item + " ");
        }

        StdOut.println();
    }


}

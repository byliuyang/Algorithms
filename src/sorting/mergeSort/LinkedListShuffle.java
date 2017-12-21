package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Divide and conquer algorithm that randomly
 * shuffles a linked list
 */
public class LinkedListShuffle {
    public static void main(String[] args) {
        // Size of array
        int N = Integer.parseInt(args[0]);

        // Generate a linked list
        Node head = sortedList(N);

        // Shuffle the linked list
        head = shuffle(head);

        // Print the linked list items,
        // on a single line
        //        show(head);
    }

    public static Node sortedList(int N) {

        // Keep head of the list
        Node head = null;

        // Create a descending sorted list
        for (int i = 0; i < N; i++) {
            Node oldHead = head;
            head = new Node();
            head.item = i;
            head.next = oldHead;
        }

        return head;
    }

    public static Node shuffle(Node head) {
        // return sorted node when list is empty
        // or has only one node
        if (head == null || head.next == null) return head;

        // Get the middle node
        Node mid = middle(head);

        // Get the second list
        Node second = mid.next;
        mid.next = null;

        Node first = shuffle(head);
        second = shuffle(second);

        return merge(first, second);
    }

    private static Node merge(Node first, Node second) {


        Node curr = null; // tail of resultant list
        Node head = null; // head of resultant list

        while (true) {


            if (first == null) {
                // append the second list at the end of resultant list
                // when the first list exhausted
                curr.next = second;
                return head;
            } else if (second == null) {

                // append the first list at the end of resultant list
                // when the second list exhausted

                Node temp = tail(second);

                curr.next = first;
                Node firstTail = tail(first);
                firstTail.next = temp;

                return head;
            } else if (StdRandom.uniform() < 0.5) {

                // append the head of first list to
                // the resultant list
                if (curr == null) {
                    curr = first;
                    head = curr;
                } else {
                    curr.next = first;
                    curr = curr.next;
                }

                first = first.next;
            } else {

                // append the head of second list to
                // the resultant list
                if (curr == null) {
                    curr = second;
                    head = curr;
                } else {
                    curr.next = second;
                    curr = curr.next;
                }

                second = second.next;
            }
        }
    }

    public static void show(Node head) {
        // Print list items, on a single line
        for (Node curr = head; curr != null; curr = curr.next)
            StdOut.print(curr.item + " ");
        StdOut.println();
    }

    private static Node middle(Node head) {
        // Traverse the list for middle node
        int N = size(head);
        int mid = N / 2;

        Node curr = head;
        for (int i = 1; i < mid; i++)
            curr = curr.next;

        return curr;
    }

    private static Node tail(Node head) {
        // Traverse the list for tail
        if (head == null) return null;

        Node tail = head;

        while (tail.next != null) tail = tail.next;

        return tail;
    }

    public static int size(Node head) {
        // Calculate the size of list
        int i = 0;
        for (Node curr = head; curr != null; curr = curr.next)
            i++;
        return i;
    }

    private static class Node {
        Comparable item;
        Node       next;
    }
}

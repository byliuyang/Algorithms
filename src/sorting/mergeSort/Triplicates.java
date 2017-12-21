package sorting.mergeSort;

import libs.StdOut;
import libs.StdRandom;

/**
 * Determine if there is any name common to all three lists,
 * and if so, return the first such name
 */
public class Triplicates {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        // Generate list of names
        Node first = randomNames(N);

        Node second = randomNames(N);

        Node third = randomNames(N);

        // Find name common to all three list
        StdOut.println(triplicates(first, second, third));
    }

    public static Node randomNames(int N) {
        int MAX = 10000;

        Node head = null;
        for (int i = 0; i < N; i++) {
            Node oldHead = head;
            head = new Node();
            head.item = Integer.toString(StdRandom.uniform(-MAX, MAX));
            head.next = oldHead;
        }

        return head;
    }

    public static String triplicates(Node first, Node second, Node third) {

        // Sort the arrays
        second = LinkedListMerge.sort(second);
        third = LinkedListMerge.sort(third);

        int sN = size(second);
        int tN = size(third);

        // Traverse the first array
        for (Node curr = first; curr != null; curr = curr.next) {
            // Search the name in the second and the third array
            String name = curr.item.toString();
            if (binarySearch(second, sN, name) != -1 && binarySearch(third, tN, name) != -1) return name;
        }

        return "";
    }

    private static int size(Node head) {
        if (head == null) return 0;

        int size = 0;
        for (Node curr = head; curr != null; curr = curr.next)
            size++;

        return size;
    }

    private static int binarySearch(Node head, int N, Comparable target) {
        // perform binary search between a[lo...hi]
        int lo = 0, hi = N - 1;

        while (lo <= hi) {

            // Find the middle item

            int mid = (hi + lo) / 2;

            Node node = node(head, (hi - lo) / 2);
            Comparable item = node.item;

            if (target.equals(item))
                // find the target
                return mid;
            else if (less(target, item)) {
                // search the lower half
                hi = mid - 1;
            } else {
                // search the higher half
                lo = mid + 1;
                head = node.next;
            }
        }

        return -1;
    }

    private static Node node(Node head, int pos) {
        // find node at pos
        for (int i = 0; i < pos; i++)
            head = head.next;

        return head;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

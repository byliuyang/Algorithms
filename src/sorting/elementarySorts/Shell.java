package sorting.elementarySorts;

import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

/**
 * Shell sort
 */
public class Shell {
    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int N = a.length; // Size of array

        // Save all h values in the stack
        Stack<Integer> hStack = new Stack<>();

        for (int i = 1; i < N / 3; i = 3 * i + 1) {
            hStack.push(i);
        }

        while (!hStack.isEmpty()) {
            int h = hStack.pop();

            // double compare = 0.0;
            for (int i = h; i < N; i++)
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    // compare++;
                    exch(a, j, j - h);
                }
            // StdOut.print(compare / N + " ");
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void show(Comparable[] a) {
        // Print the array, on a single line
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}

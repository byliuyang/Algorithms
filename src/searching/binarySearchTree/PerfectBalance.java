package searching.binarySearchTree;

import java.util.Arrays;

/**
 * Inserts keys into an initially empty BST such that the tree produced is equivalent to binary
 * search, in the sense that the sequence of compares done in the search for any key in the BST
 * is the same as the sequence of compares used by binary search for the same set of keys
 */
public class PerfectBalance {
    public static void main(String[] args) {
        String[] keys = {"E", "X", "B", "M", "P", "L", "E"};
        Arrays.sort(keys);
        BST<String, Integer> st = new BST<>();
        insert(keys, st, 0, keys.length - 1);
    }

    public static void insert(String[] keys, BST<String, Integer> st, int lo, int hi) {
        if (hi < lo) return;
        int mid = (lo + hi) / 2;
        st.put(keys[mid], mid);
        insert(keys, st, lo, mid - 1);
        insert(keys, st, mid + 1, hi);
    }
}

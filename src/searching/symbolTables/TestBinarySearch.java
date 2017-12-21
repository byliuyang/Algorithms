package searching.symbolTables;

import libs.StdIn;
import libs.StdOut;

/**
 * Test client for ordered symbol table
 */
public class TestBinarySearch {
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st;
        st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        StdOut.println("Min: " + st.min());
        StdOut.println("Max: " + st.max());

        String key1 = args[0];
        StdOut.println("Floor: " + st.floor(key1));

        String key2 = args[1];
        StdOut.println("Ceiling: " + st.ceiling(key2));

        StdOut.println("Select: " + st.select(5));

        String key3 = args[2];
        StdOut.println("Rank: " + st.rank(key3));

        st.deleteMin();
        StdOut.println("After deleteMin: " + st.min());

        st.deleteMax();
        StdOut.println("DeleteMax: " + st.max());
    }
}

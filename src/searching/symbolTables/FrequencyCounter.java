package searching.symbolTables;

import libs.StdIn;
import libs.StdOut;

/**
 * A symbol table client that find number of occurrences of each
 * string in a sequence of string from standard input, then iterates through
 * the keys to find the one that occurs the most frequently
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        // key length cut-off
        int minlen = Integer.parseInt(args[0]);

        ArrayST<String, Integer> st = new ArrayST<>();
        while (!StdIn.isEmpty()) {
            // Build symbol table and count frequencies.
            String word = StdIn.readString();
            // ignore short keys
            if (word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max)) max = word;
        StdOut.println(max + " " + st.get(max));
    }
}

package searching.symbolTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

/**
 * Compilation: SequentialSearchST.java
 * Execution: SequentialSearchST
 * Dependencies:
 *
 * Symbol table implementation with sequential search in an unordered linked
 * list of key-value pairs.
 *
 * % more tinyST.txt
 * S E A R C H E X A M P L E
 *
 * % java SequentialSearchST < tinyST.txt
 *
 *
 * Sequential search (in an unordered linked list)
 */
public class SequentialSearchST<Key, Value> {
    // number of key-value pairs
    private int  n;
    // the linked list of key-value pairs
    private Node first;
    private Node cache;

    /**
     * Initialize empty symbol table
     */
    public SequentialSearchST() {}

    /**
     * Unit tests <tt>SequentialSearchST</tt> data type.
     */
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st;
        st = new SequentialSearchST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }


    public void put(Key key, Value val) {
        if (cache != null && cache.key.equals(key)) {
            cache.val = val;
            return;
        }

        // Search for key. Update value if found; grow table if new
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                // search hit: update value
                cache = x;
                x.val = val;
                return;
            }
        // search miss: add new node
        first = new Node(key, val, first);
        cache = first;
        n++;
    }

    /**
     * Return the value associated with the given key in this symbol table.
     *
     * @param key the key
     *
     * @return the value associated with the given key in the symbol table
     * and <tt>null</tt> if the key is not in the symbol
     * table
     *
     * @throws NullPointerException if the <tt>key</tt> is <tt>null</tt>
     */
    public Value get(Key key) {
        if (cache != null && cache.key.equals(key)) return cache.val;

        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                // search hit
                cache = x;
                return x.val;
            }
        // search miss
        return null;
    }

    /**
     * Return all keys in this symbol table as <tt>Iterable</tt>
     * To iterate over all of keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for(Key key : st.keys())</tt>
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            q.enqueue(x.key);
        return q;
    }

    /**
     * Return true if this symbol table contains the specific key.
     *
     * @param key the key
     *
     * @return <tt>true</tt> if the symbol table contains <tt>key</tt>;
     * <tt>false</tt> otherwise
     *
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("argument to contain() is null");
        return get(key) != null;
    }

    /**
     * Remove the specific key and its associated value from this symbol
     * table (if the key is in this symbol table)
     *
     * @param key the key
     *
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("argument to delete() is null");
        if (isEmpty()) return;

        Node last = null;
        Node x = first;
        while (x != null && !x.key.equals(key)) {
            last = x;
            x = x.next;
        }
        if (x == null) return;
        if (last == null) first = first.next;
        else last.next = x.next;
        n--;
    }

    /**
     * Return true if symbol table is empty
     *
     * @return <tt>true</tt> if symbol table is empty; <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return number of key-value pairs in this symbol table
     *
     * @return number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    // a helper linked list data type
    private class Node {
        // linked-list node
        Key   key;
        Value val;
        Node  next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}

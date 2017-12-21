package searching.symbolTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

/******************************************************************************
 *
 * Compilation: javac OrderedSequentialSearchST.java
 * Execution: java OrderedSequentialSearchST
 * Dependencies:
 *
 * Symbol table implemented with ordered linked list
 *
 ******************************************************************************/

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    // the linked-list of key-value pairs
    private Node first;
    // number of key-value pairs in the table
    private int  N;

    public OrderedSequentialSearchST() {}

    /**
     * Test routing
     */
    public static void main(String[] args) {
        OrderedSequentialSearchST<String, Integer> st;
        st = new OrderedSequentialSearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

    /**
     * Put key-value pair into the table (remove key from table if value is null)
     *
     * @param key key
     * @param val value
     */
    public void put(Key key, Value val) {
        if (key == null) throw new RuntimeException("Key cannot be null");
        if (val == null) delete(key);
        Node last = null;
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) {
            last = x;
            x = x.next;
        }
        if (x != null && x.key.compareTo(key) == 0) {
            x.val = val;
            return;
        } else if (x == first) first = new Node(key, val, first);
        else if (last != null) last.next = new Node(key, val, x);
        N++;
    }

    /**
     * Remove key (and its value) from table
     *
     * @param key key
     */
    public void delete(Key key) {
        if (key == null) return;
        else if (isEmpty()) return;

        Node last = null;
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) {
            last = x;
            x = x.next;
        }
        if (last == null) first = first.next;
        else if (x != null && x.key.equals(key)) last.next = x.next;
        N--;

    }

    /**
     * Return <tt>true</tt> if the table is empty, <tt>false</tt> otherwise
     *
     * @return <tt>true</tt> if the table is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of key-value pairs in the table
     *
     * @return the number of key-value pairs in the table
     */
    public int size() {
        return N;
    }

    /**
     * Return value paired with key (null if key is absent)
     *
     * @param key key
     *
     * @return value paired with key
     */
    public Value get(Key key) {
        if (key == null) throw new RuntimeException("Key cannot be null");
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) {
            x = x.next;
        }
        if (x != null && x.key.equals(key)) return x.val;
        return null;
    }

    /**
     * Return all keys in the table, in sorted order
     *
     * @return all keys in the table, in sorted order
     */
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            q.enqueue(x.key);
        return q;
    }

    /**
     * Return the largest key less than or equal to the <tt>key</tt>
     *
     * @param key key
     *
     * @return the largest key less than or equal to the key
     */
    public Key floor(Key key) {
        if (isEmpty()) return null;
        Node x = first;
        while (x.next != null && x.next.key.compareTo(key) < 0) x = x.next;
        return x.key;
    }

    /**
     * Return the smallest key greater than or equal to the <tt>key</tt>
     *
     * @param key key
     *
     * @return the smallest key greater than or equal to the <tt>key</tt>
     */
    public Key ceiling(Key key) {
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) x = x.next;
        return x.key;
    }

    /**
     * Delete the smallest key
     */
    public void deleteMin() {
        delete(min());
    }

    /**
     * Return the smallest key
     *
     * @return the smallest key
     */
    public Key min() {
        if (isEmpty()) return null;
        return first.key;
    }

    /**
     * Delete the largest key
     */
    public void deleteMax() {
        delete(max());
    }

    /**
     * Return the largest key
     *
     * @return the largest key
     */
    public Key max() {
        if (isEmpty()) return null;
        Node x = first;
        while (x.next != null) x = x.next;
        return x.key;
    }

    /**
     * Return keys in a[<tt>lo</tt>..hi] in sorted order
     *
     * @param lo low
     * @param hi high
     *
     * @return keys in a[<tt>lo</tt>..hi] in sorted order
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        int j = rank(lo);
        Node x = first;
        for (int i = 0; i < j && x != null; i++)
            x = x.next;
        for (int i = j; i < rank(hi); i++) {
            q.enqueue(x.key);
            x = x.next;
        }
        if (contains(hi)) q.enqueue(x.next.key);
        return q;
    }

    /**
     * Return <tt>true</tt> if a value is paired with key, <tt>false</tt> otherwise
     *
     * @param key key
     *
     * @return <tt>true</tt> if a value is paired with key, <tt>false</tt> otherwise
     */
    public boolean contains(Key key) {
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) x = x.next;
        return (x != null && x.key.equals(key));
    }

    /**
     * Return the number of keys less than the <tt>key</tt>
     *
     * @param key key
     *
     * @return the number of keys less than the <tt>key</tt>
     */
    public int rank(Key key) {
        int i = 0;
        Node x = first;
        while (x != null && x.key.compareTo(key) < 0) {
            i++;
            x = x.next;
        }
        return i;
    }

    /**
     * Return key of rank <tt>k</tt>
     *
     * @param k rank k
     *
     * @return key of rank <tt>k</tt>
     */
    public Key select(int k) {
        Node x = first;
        for (int i = 0; i < k && x != null; i++)
            x = x.next;

        if (x == null) return null;
        return x.key;
    }

    /**
     * Linked-list node
     */
    private class Node {
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

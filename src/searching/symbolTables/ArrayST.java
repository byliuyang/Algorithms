package searching.symbolTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

/******************************************************************************
 *
 * Compilation: javac ArrayST.java
 * Execution: java Array < tinyTale.txt
 * Dependencies: StdIn.java StdOut.java Queue.java
 *
 * Unordered symbol table implemented with arrays
 *
 ******************************************************************************/

public class ArrayST<Key, Value> {
    private Key[]   keys;
    private Value[] vals;
    private int     N;

    /**
     * Create a symbol table
     */
    public ArrayST() {
        this(1);
    }

    /**
     * Create a symbol table with given <tt>capacity</tt>
     */
    public ArrayST(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
    }

    /**************************************************************************
     * Test routing
     **************************************************************************/

    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        st.delete("it");

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

    /**
     * Put <tt>key</tt>-<tt>value</tt> pair into the table
     * (remove key from table if value is null)
     *
     * @param key key
     * @param val value
     */
    public void put(Key key, Value val) {
        // Key can not be null
        if (key == null) throw new RuntimeException("key cannot be null");
        // Remove key from table if value is null
        if (val == null) {
            delete(key);
            return;
        }
        // set to key-value pair to new value
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        // Auto-resizing
        if (N == keys.length) resize(keys.length * 2);
        keys[N] = key;
        vals[N] = val;
        N++;
    }

    /**
     * Return value paired with the <tt>key</tt>, <tt>null</tt> is key is absent
     *
     * @param key key
     *
     * @return value paired with the <tt>key</tt>
     */
    public Value get(Key key) {
        if (key == null) throw new RuntimeException("key cannot be null");
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key)) {
                // move to front
                key = keys[i];
                Value val = vals[i];
                for (int j = i; j > 0; j--) {
                    keys[j] = keys[j - 1];
                    vals[j] = vals[j - 1];
                }
                keys[0] = key;
                vals[0] = val;
                return val;
            }
        return null;
    }

    /**
     * Return all keys in the table
     *
     * @return all keys in the table
     */
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++)
            q.enqueue(keys[i]);
        return q;
    }

    /**
     * Remove key and its value from the table
     *
     * @param key key
     */
    public void delete(Key key) {
        if (key == null) throw new RuntimeException("key cannot be null");
        if (isEmpty()) throw new IndexOutOfBoundsException("underflow");
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key)) {
                exch(i, --N);
                keys[N] = null;
                vals[N] = null;
                if (N > 0 && N == keys.length / 4) resize(keys.length / 2);
                return;
            }
    }

    /**
     * Return <tt>true</tt> if table is empty, <tt>false</tt> otherwise
     *
     * @return <tt>true</tt> if table is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of keys in the table
     *
     * @return the number of keys in the table
     */
    public int size() {
        return N;
    }

    public boolean contains(Key key) {
        if (key == null) return false;
        for (int i = 0; i < N; i++)
            if (keys[i].equals(key)) {
                return true;
            }
        return false;
    }

    /**************************************************************************
     * General helper functions
     **************************************************************************/

    private void resize(int capacity) {
        Key[] tempKeys = (Key[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            tempKeys[i] = keys[i];

        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            tempVals[i] = vals[i];

        keys = tempKeys;
        vals = tempVals;
    }

    private void exch(int i, int j) {
        Key swapkey = keys[i];
        keys[i] = keys[j];
        keys[j] = swapkey;

        Value swapval = vals[i];
        vals[i] = vals[j];
        vals[j] = swapval;
    }
}

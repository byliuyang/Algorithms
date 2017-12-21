package searching.symbolTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

/**
 * BinarySearch in an ordered array
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[]   keys;
    private Value[] vals;
    private int     N;
    private int     cache;

    public BinarySearchST() {
        this(1);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * Test routing
     */
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st;
        st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

    public void put(Key key, Value val) {
        int i;
        if (!isEmpty() && key.compareTo(keys[N - 1]) > 0) i = N;
        else i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if (N == keys.length) resize(keys.length * 2);
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;

        assert check();
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public int rank(Key key) {
        if (!isEmpty() && key.compareTo(keys[cache]) == 0) return cache;

        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else {
                cache = mid;
                return mid;
            }
        }
        cache = lo;
        return lo;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi)) q.enqueue(keys[rank(hi)]);
        return q;
    }

    public void deleteMin() {
        delete(min());
    }

    public Key min() {
        return keys[0];
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("argument to delete() is null");
        if (isEmpty()) return;

        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) return;

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;

        if (N > 0 && N == keys.length / 4) resize(keys.length / 2);

        assert check();
    }

    private void resize(int capacity) {
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        for (int i = 0; i < N; i++)
            tempKeys[i] = keys[i];

        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            tempVals[i] = vals[i];

        keys = tempKeys;
        vals = tempVals;
    }

    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("argument to floor() is null");
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i - 1];
    }

    public void deleteMax() {
        delete(max());
    }

    public Key max() {
        return keys[N - 1];
    }

    /**************************************************************************
     * Check internal invariants
     **************************************************************************/

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

    public Key select(int k) {
        return keys[k];
    }
}

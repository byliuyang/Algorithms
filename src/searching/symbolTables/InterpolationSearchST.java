package searching.symbolTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

/**
 * InterpolationSearch in an ordered array
 */
public class InterpolationSearchST<Value> {
    private Double[]   keys;
    private Value[] vals;
    private int     N;

    public InterpolationSearchST() {
        this(1);
    }

    public InterpolationSearchST(int capacity) {
        keys = (Double[]) new Comparable[capacity];
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

    public void put(Double key, Value val) {
        int i = rank(key);
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
    }

    public int rank(Double key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (int)((key - keys[lo]) / (keys[lo] - keys[hi]));
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    private void resize(int capacity) {
        Double[] tempDoubles = (Double[]) new Comparable[capacity];
        for (int i = 0; i < N; i++)
            tempDoubles[i] = keys[i];

        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            tempVals[i] = vals[i];

        keys = tempDoubles;
        vals = tempVals;
    }

    public Double select(int k) {
        return keys[k];
    }

    public Double ceiling(Double key) {
        int i = rank(key);
        return keys[i];
    }

    public int size(Double lo, Double hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Double key) {
        return get(key) != null;
    }

    public Value get(Double key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Double> keys() {
        return keys(min(), max());
    }

    public Iterable<Double> keys(Double lo, Double hi) {
        Queue<Double> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi)) q.enqueue(keys[rank(hi)]);
        return q;
    }

    public Double min() {
        return keys[0];
    }

    public Double max() {
        return keys[N - 1];
    }

    public void deleteMin() {
        delete(min());
    }

    public void delete(Double key) {
        if (key == null)
            throw new NullPointerException("argument to delete() is null");
        if (isEmpty()) return;

        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) return;

        for (int j = i; j < N - 1; i++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        N--;
        keys[N] = null;
        vals[N] = null;

        if (N > 0 && N == keys.length / 4) resize(keys.length / 2);
    }

    public Double floor(Double key) {
        if (key == null)
            throw new NullPointerException("argument to floor() is null");
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i - 1];
    }

    public void deleMax() {
        delete(max());
    }
}

package searching.hashTables;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;

public class LinearProbingHashST<Key, Value> {

    private static final int INIT_CAPACITY = 4;

    private int N;
    private int M;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    private LinearProbingHashST(int capacity) {
        M = capacity;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put is null");

        if (value == null) {
            delete(key);
            return;
        }

        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1 % M))
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains is null");
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete is null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyRedo = keys[i];
            Value valueRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyRedo, valueRedo);
            i = (i + 1) % M;
        }

        N--;

        if (N > 0 && N <= M / 8) resize(M / 2);
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                st.put(keys[i], values[i]);
        keys = st.keys;
        values = st.values;
        M = st.M;
    }

    private Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++)
            if(keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        for(int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s: st.keys())
            StdOut.printf("%s %d\n", s);
    }
}

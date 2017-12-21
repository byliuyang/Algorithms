package searching.hashTables;

import searching.symbolTables.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N; // number of key-value pairs
    private int M; // size of hash table
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for(int i = 0; i < M; i++) {
            for(Key key: st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        N = temp.N;
        M = temp.M;
        st = temp.st;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }

        if(N >= 10 * M) resize(2 * M);

        int i = hash(key);
        if(!st[i].contains(key)) N++;
        st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if(st[i].contains(key)) N--;
        st[i].delete(key);

        if(M > INIT_CAPACITY && N < 10 * M) resize(M / 2);
      }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return st[hash(key)].get(key);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, String> separateChainingHashST = new SeparateChainingHashST<>();
        separateChainingHashST.put("a", "h");
        separateChainingHashST.put("b", "i");
        separateChainingHashST.put("c", "j");
        separateChainingHashST.put("d", "k");
        separateChainingHashST.put("e", "l");
        separateChainingHashST.put("f", "m");

        System.out.println( separateChainingHashST.get("a"));
        System.out.println( separateChainingHashST.get("b"));
        System.out.println( separateChainingHashST.get("c"));
        System.out.println( separateChainingHashST.get("d"));
        System.out.println( separateChainingHashST.get("e"));
        System.out.println( separateChainingHashST.get("f"));
    }
}

package searching.binarySearchTree;

import fundamentals.bagQueueStack.queue.Queue;
import libs.StdIn;
import libs.StdOut;
import libs.StdRandom;

/**
 * Symbol table implementation with binary search tree
 */
public class ThreadedST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST
    private Node cache;

    public static void main(String[] args) {
        ThreadedST<String, Integer> st = new ThreadedST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        StdOut.println("Height linear time = " + st.heightTime());
        StdOut.println("Height linear space = " + st.heightSpace());
        StdOut.println("Average compares time = " + st.avgComparesTime());
        StdOut.println("Average compares space = " + st.avgComparesSpace());
        StdOut.println("Random key = " + st.randomKey());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        if (cache != null && key.compareTo(cache.key) == 0) cache.val = val;
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val, 0);
    }

    private Node put(Node x, Key key, Value val, int level) {
        // Change key's value to val if key in in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        int i = rank(key);
        if (x == null)
            return new Node(key, val, select(root, i - 1), select(root, i + 1), 1, 0, level);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val, level + 1);
        else if (cmp > 0) x.right = put(x.right, key, val, level + 1);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(heightSpace(x.left), heightSpace(x.right)) + 1;
        x.I = level + inPathLenSpace(x.left) + inPathLenSpace(x.right);
        cache = x;
        return x;
    }

    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        if (cache != null && key.compareTo(cache.key) == 0) return cache.val;
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else {
            cache = x;
            return x.val;
        }
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("argument to floor() is null");
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("argument to ceiling() is null");
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // return Node containing key of rank k
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        if (key == null) throw new NullPointerException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        // return number of keys less than x.key in the subtree rooted at x
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        int i = rank(x.key);
        x.prev = select(root, i - 1);
        x.next = select(root, i + 1);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(heightSpace(x.left), heightSpace(x.right)) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        int i = rank(x.key);
        x.prev = select(root, i - 1);
        x.next = select(root, i + 1);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        int i = rank(x.key);
        x.prev = select(root, i - 1);
        x.next = select(root, i + 1);
        x.N = size(x.left) + size(x.right) + 1;
        x.H = Math.max(heightSpace(x.left), heightSpace(x.right)) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) throw new NullPointerException("argument to keys() is null");
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int heightTime() {
        return heightTime(root);
    }

    private int heightTime(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(heightTime(x.left), heightTime(x.right));
    }

    public int heightSpace() {
        return heightSpace(root);
    }

    private int heightSpace(Node x) {
        if (x == null) return 0;
        else return x.H;
    }


    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains" + "() is " + "null");
        return get(key) != null;
    }

    public int inPathLenTime() {
        return inPathLenTime(root, 0);
    }

    private int inPathLenTime(Node x, int level) {
        if (x == null) return 0;
        else if (x.left == null && x.right == null) return level;
        return level + inPathLenTime(x.left, level + 1) + inPathLenTime(x.right, level + 1);
    }

    public int inPathLenSpace() {
        return inPathLenSpace(root);
    }

    private int inPathLenSpace(Node x) {
        if (x == null) return 0;
        return x.I;
    }

    public int avgComparesTime() {
        return inPathLenTime() / size(root) + 1;
    }

    public int avgComparesSpace() {
        return inPathLenSpace(root) / size(root) + 1;
    }

    /**
     * Returns a random key from the symbol table in time proportional to the tree height, in the
     * worst case
     *
     * @return a random key from the symbol table in time proportional to the tree height, in the
     * worst case
     */
    public Key randomKey() {
        Node x = randomKey(root);
        if (x == null) return null;
        return x.key;
    }

    /**
     * Returns a random <tt>Node</tt> from the symbol table in time proportional to the tree
     * height, in the worst case
     *
     * @return a random <tt>Node</tt> from the symbol table in time proportional to the tree height,
     * in the worst case
     */
    private Node randomKey(Node x) {
        if (x == null) return null;
        double r = StdRandom.uniform();
        if (x.left == null && x.right == null) return x;
        else if (x.left == null) {
            if (r > 0.5) return randomKey(x.right);
            return x;
        } else if (x.right == null) {
            if (r < 0.5) return randomKey(x.left);
            return x;
        } else {
            if (r < 0.33) return randomKey(x.left);
            else if (r < 0.66) return x;
            else return randomKey(x.right);
        }
    }

    /**
     * Return true if the subtree count field N is consistent in the data structure rooted at
     * that node, false otherwise
     *
     * @param x root
     *
     * @return true if the subtree count field N is consistent in the data structure rooted at
     * that node, false otherwise
     */
    private boolean isBinaryTree(Node x) {
        return x == null || (size(x) != size(x.left) + size(x.right) + 1) && isBinaryTree(x.left)
                            && isBinaryTree(x.right);
    }

    /**
     * returns true if all the keys in the tree are between min and max; min and max are indeed
     * the smallest and largest keys in the tree, respectively; and the BST ordering property
     * holds for all keys in the tree; false otherwise.
     *
     * @param x   root
     * @param min minimum key
     * @param max maximum key
     *
     * @return true if all the keys in the tree are between min and max; min and max are indeed
     * the smallest and largest keys in the tree, respectively; and the BST ordering property
     * holds for all keys in the tree; false otherwise.
     */
    private boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    }

    /**
     * Returns true if there are no equal keys in the binary tree rooted at the argu- ment node,
     * false otherwise.
     *
     * @param x root
     *
     * @return true if there are no equal keys in the binary tree rooted at the argu- ment node,
     * false otherwise.
     */
    private boolean hasNoDuplicates(Node x) {
        for (int i = 1; i < size(); i++)
            if (select(i).compareTo(select(i - 1)) <= 0) return false;
        return true;
    }

    /**
     * Return <tt>true</tt> when for all i from 0 to size()-1, whether i is equal to rank(select
     * (i)) and, for all keys in the BST, whether key is equal to select(rank(key)),
     * <tt>false</tt> otherwise
     *
     * @param x root
     *
     * @return <tt>true</tt> when for all i from 0 to size()-1, whether i is equal to rank(select
     * (i)) and, for all keys in the BST, whether key is equal to select(rank(key)),
     * <tt>false</tt> otherwise
     */
    private boolean isRankConsistent(Node x) {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    /**
     * Returns true if the argument node is the root of a binary search tree, false otherwise.
     *
     * @return true if the argument node is the root of a binary search tree, false otherwise.
     */
    private boolean isBST() {
        if (!isBinaryTree(root)) return false;
        if (!isOrdered(root, min(), max())) return false;
        if (!hasNoDuplicates(root)) return false;
        return true;
    }

    private class Node {
        Key   key; // key
        Value val; // associated value
        Node  left, right; // links to subtrees
        Node prev, next;
        int N;  // # nodes in subtree rooted here
        int H; // height of subtree
        int I; // Internal path length

        public Node(Key key, Value val, Node prev, Node next, int n, int h, int i) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
            N = n;
            H = h;
            I = i;
        }
    }
}

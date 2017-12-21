package searching.binarySearchTree;

import libs.StdIn;
import libs.StdOut;

/**
 * Symbol table implementation with binary search tree
 */
public class IterativeBST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST

    public static void main(String[] args) {
        IterativeBST<String, Integer> st = new IterativeBST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        StdOut.println("Min = " + st.min());
        StdOut.println("Max = " + st.max());

        StdOut.println();
        StdOut.println("Rank = " + st.rank("X"));
        StdOut.println("Rank = " + st.rank("Z"));

        StdOut.println();
        StdOut.println("Select = " + st.select(6));
        StdOut.println("Select = " + st.select(2));
        StdOut.println("Select = " + st.select(4));

        StdOut.println();
        StdOut.println("Floor = " + st.floor("A"));
        StdOut.println("Floor = " + st.floor("F"));
        StdOut.println("Floor = " + st.floor("L"));
        StdOut.println("Floor = " + st.floor("O"));
        StdOut.println("Floor = " + st.floor("Z"));

        StdOut.println();
        StdOut.println("Ceiling = " + st.ceiling("X"));
        StdOut.println("Ceiling = " + st.ceiling("Y"));
        StdOut.println("Ceiling = " + st.ceiling("O"));
        StdOut.println("Ceiling = " + st.ceiling("Q"));
        StdOut.println("Ceiling = " + st.ceiling("A"));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }

    public void put(Key key, Value val) {
        Node z = new Node(key, val);
        if (root == null) {
            root = z;
            return;
        }
        Node parent = null;
        Node x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.val = val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = z;
        else parent.right = z;
    }


    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        while (x.left != null) x = x.left;
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != null) x = x.right;
        return x;
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("argument to floor() is null");
        Node x = root;
        Node min = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.key;
            else if (cmp < 0) {
                if (min == x) min = x.left;
                x = x.left;
            } else {
                min = x;
                x = x.right;
            }
        }
        if (min == null) return null;
        return min.key;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("argument to ceiling() is null");
        Node x = root;
        Node max = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.key;
            else if (cmp > 0) {
                if (max == x) max = x.right;
                x = x.right;
            } else {
                max = x;
                x = x.left;
            }
        }
        if (max == null) return null;
        return max.key;
    }

    public Key select(int k) {
        Node x = root;
        while (x != null) {
            int t = size(x.left);
            if (t > k) x = x.left;
            else if (t < k) {
                x = x.right;
                k = k - t - 1;
            } else return x.key;
        }
        return null;
    }

    public int rank(Key key) {
        if (key == null) throw new NullPointerException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        // return number of keys less than x.key in the subtree rooted at x
        int cnt = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                cnt += 1 + size(x.left);
                x = x.right;
            } else return cnt + size(x.left);
        }
        return cnt;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains" + "() is " + "null");
        return get(key) != null;
    }

    private class Node {
        Key   key; // key
        Value val; // associated value
        Node  left, right; // links to subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }
}

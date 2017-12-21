package sorting.priorityQueues;

import libs.StdOut;
import libs.StdRandom;

/***********************************************************************
 *
 * Compilation: javac TriplyLinkedMaxPQ.java
 * Execution: java TriplyLinkedMaxPQ
 * Dependencies: StdRandom StdOut
 *
 * Triply linked priority queue implemented with binary tree
 *
 ***********************************************************************/

public class TriplyLinkedMaxPQ<Key extends Comparable<Key>> {
    // elements
    private BinaryTree pq;
    // number of elements
    private int        n;

    /**
     * Create a triply linked max priority queue
     */
    public TriplyLinkedMaxPQ() {
        pq = null;
        n = 0;
    }

    /***********************************************************************
     * Test routing
     ***********************************************************************/
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        TriplyLinkedMaxPQ<Integer> pq = new TriplyLinkedMaxPQ<>();

        for (int i = 0; i < N; i++) {
            pq.insert(StdRandom.uniform(0, N));
        }

        while (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        StdOut.println();
    }

    /**
     * Return true when priority queue is empty, false otherwise
     *
     * @return true when priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return pq == null;
    }

    /**
     * Insert <tt>key</tt> into priority queue
     *
     * @param key to be inserted
     */
    public void insert(Key key) {
        if (pq == null) {
            pq = new BinaryTree(key);
            return;
        }

        pq.insert(key);

        swim(pq.getLastInserted());
    }

    /***********************************************************************
     * Heap helper functions
     ***********************************************************************/
    private void swim(BinaryTree bottom) {
        BinaryTree parent = bottom.parent;
        while (parent != null && less(parent.item, bottom.item)) {
            exch(parent, bottom);
            bottom = parent;
            parent = parent.parent;
        }
    }

    /***********************************************************************
     * General helper functions
     ***********************************************************************/
    private void exch(BinaryTree i, BinaryTree j) {
        Key swap = i.item;
        i.item = j.item;
        j.item = swap;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Return and delete the maximum key in the priority queue
     *
     * @return the maximum key in the priority queue
     */
    public Key delMax() {
        if (isEmpty()) return null;

        Key key = pq.item;
        if (pq.hasChild()) {
            BinaryTree lastInserted = pq.getLastInserted();
            exch(pq, lastInserted);
            lastInserted.parent.removeChild(lastInserted);

            sink(pq);

        } else {
            pq = null;
        }

        return key;
    }

    private void sink(BinaryTree top) {
        while (top.hasChild()) {
            BinaryTree greaterChild;
            if (top.left == null) greaterChild = top.right;
            else if (top.right == null) greaterChild = top.left;
            else if (less(top.right.item, top.left.item)) greaterChild = top.left;
            else greaterChild = top.right;

            if (less(greaterChild.item, top.item)) break;

            exch(top, greaterChild);
            top = greaterChild;
        }
    }

    /**
     * Return number of elements in the priority queue
     *
     * @return number of elements in the priority queue
     */
    public int size() {
        return n;
    }

    /***********************************************************************
     * Helper abstract data types
     ***********************************************************************/
    private class BinaryTree {
        Key        item;
        // parent node
        BinaryTree parent;
        // left child
        BinaryTree left;
        // right child
        BinaryTree right;
        // child containing last inserted item
        BinaryTree lastInserted;

        /**
         * Create a binary tree with item
         *
         * @param item of the tree
         */
        BinaryTree(Key item) {
            this.item = item;
        }

        /**
         * Insert new item into the tree
         *
         * @param item to be inserted
         */
        public void insert(Key item) {
            // insert as left child when having no child yet
            if (!hasChild()) {
                left = new BinaryTree(item);
                left.parent = this;

                lastInserted = left;
            }
            // insert as left child when having only right child
            else if (left == null) {
                left = new BinaryTree(item);
                left.parent = this;
                lastInserted = left;
            }
            // insert as right child when having only left child
            else if (right == null) {
                right = new BinaryTree(item);
                right.parent = this;
                lastInserted = right;
            }
            // insert into shallow child tree when having both children
            else if (left.getHeight() <= right.getHeight()) {
                left.insert(item);
                lastInserted = left;
            } else {
                right.insert(item);
                lastInserted = right;
            }
        }

        /**
         * Return the height of the tree
         *
         * @return the height of the tree
         */
        public int getHeight() {
            if (!hasChild()) return 0;
            else if (left == null) return right.getHeight() + 1;
            else if (right == null) return left.getHeight() + 1;

            return Math.max(left.getHeight(), right.getHeight()) + 1;
        }

        /**
         * Return <tt>true</tt> when the have at least one child, false other wise
         *
         * @return <tt>true</tt> when the have at least one child, false other wise
         */
        public boolean hasChild() {
            return left != null || right != null;
        }

        public void removeChild(BinaryTree child) {
            // remove left child
            if (child == left) {
                lastInserted = right;
                left = null;
            }
            // remove right child
            if (child == right) {
                lastInserted = left;
                right = null;
            }
            // detach child from parent
            child.parent = null;
        }

        /**
         * Return last child inserted into this tree
         *
         * @return last child inserted into this tree
         */
        public BinaryTree getLastInserted() {
            if (lastInserted == null) return this;

            return lastInserted.getLastInserted();
        }

    }
}
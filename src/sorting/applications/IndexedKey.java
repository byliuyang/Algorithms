package sorting.applications;

import libs.StdIn;
import libs.StdOut;
import sorting.quickSort.Quick;

/**********************************************************************************************
 *
 * Compilation: javac IndexedKey.java
 * Execution: java IndexedKey
 * Dependencies: StdIn.java StdOut.java
 *
 * Force stability.
 * Write a wrapper method that makes any sort stable by creating a new key type
 * that allows you to append each key’s index to the key, call sort(),
 * then restore the original key after the sort.
 *
 **********************************************************************************************/

public class IndexedKey implements Comparable<IndexedKey> {
    private Comparable key;
    private int        index;

    public IndexedKey(Comparable key, int index) {
        this.key = key;
        this.index = index;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        // Append index to array elements
        IndexedKey[] indexedA = new IndexedKey[n];
        for (int i = 0; i < n; i++)
            indexedA[i] = new IndexedKey(a[i], i);
        // Sort elements
        Quick.sort(indexedA);
        for (int i = 0; i < n; i++)
            // restore original key after sort
            a[i] = (String) indexedA[i].key;
        // print sorted array
        for (String s : a)
            StdOut.print(s + " ");
    }

    @Override
    public int compareTo(IndexedKey that) {
        int cmp = this.key.compareTo(that.key);
        if (cmp < 0) return -1;
        if (cmp > 0) return 1;
        if (this.index < that.index) return -1;
        if (this.index > that.index) return 1;
        return 0;
    }
}

package sorting.priorityQueues;

import libs.StdOut;
import libs.StdRandom;

/***************************************************************************************
 *
 * Compilation: javac Sample.java
 * Execution: java Sample
 * Dependencies: StdOut.java StdRandom.java
 *
 * Sampling from a discrete probability distribution
 *
 ***************************************************************************************/

public class Sample {
    private Double[] db;
    private Double[] prob;
    private int      N;

    /**
     * Create cumulative probability distribution binary tree
     *
     * @param a probability distribution for elements
     */
    public Sample(Double[] a) {
        // size of probability distribution array
        N = a.length;
        // Copy input array into instance variable
        prob = new Double[N];
        for (int i = 0; i < N; i++)
            prob[i] = a[i];
        // Create balanced binary tree
        db = new Double[N + 1];
        // Copy leaves into binary tree
        for (int k = N; k > N / 2; k--)
            db[k] = a[k - 1];
        // Compute cumulative probabilities start from first parent
        for (int k = N / 2; k > 0; k--) {
            // Have left child
            int j = k * 2;
            db[k] = a[k - 1] + db[j];
            // Have right child
            if (j < N) db[k] += db[j + 1];
        }
    }

    /***************************************************************************************
     * Test routing
     ***************************************************************************************/
    public static void main(String[] args) {
        Double[] a = new Double[20];

        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(0.0, 20.0);

        Sample s = new Sample(a);
        StdOut.println(s.random());
        s.change(5, 1.0);
        s.change(3, 6.0);
        StdOut.println(s.random());
    }

    /**
     * Generate and return index of random element from probability distribution
     *
     * @return return index of random element from probability distribution
     */
    public int random() {
        // random number
        Double p = StdRandom.uniform(0.0, db[1]);
        // start from root
        int k = 1;
        // inside balanced binary tree
        while (k <= N) {
            int j = k * 2;
            // reach leaves
            if (j > N) return k;
            // Have only left child
            if (j == N && j + 1 > N) {
                // goto left subtree
                if (less(p, db[j])) k = j;
                    // current index
                else return k;
            }
            // Have both left and right children
            // goto left when p < left child
            else if (less(p, db[j])) k = j;
                // goto center when left child <= p < right child
            else if (!less(p, db[j]) && less(p, db[k] - db[j + 1])) return k;
            else {
                // goto right when p > right child
                // p = p - center - left
                p = p - db[k] + db[j + 1];
                k = j + 1;
            }
        }
        // tree is empty
        return k;
    }

    /***************************************************************************************
     * General distribution helper function
     ***************************************************************************************/
    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Change the probability distribution at <tt>i</tt> to <tt>v</tt> and readjust
     * its parents' cumulative probabilities
     *
     * @param i probability to be adjust
     * @param v new probability distribution for prob[i]
     */
    public void change(int i, double v) {
        db[i] = v;
        adjustProb(i);
    }

    /***************************************************************************************
     * Probability distribution helper function
     ***************************************************************************************/
    private void adjustProb(int i) {
        for (int k = i / 2; k > 0; k = k / 2) {
            if (2 * k <= N) {
                int j = k * 2;
                db[k] = prob[k - 1] + db[j];
                if (j < N) db[k] += db[j + 1];
            }
        }
    }
}

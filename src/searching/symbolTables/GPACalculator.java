package searching.symbolTables;

import libs.StdIn;
import libs.StdOut;

/********************************************************************************************************
 *
 * Compilation: javac GPACalculator.java
 * Execution: java GPACalculator
 * Dependencies: StdIn.java StdOut.java
 *
 * a client that creates a symbol table mapping letter grades to numerical scores,
 * as in the table below, then reads from standard input a list of letter grades
 * and computes and prints the GPA.
 *
 ********************************************************************************************************/

public class GPACalculator {
    // letter grades from A+ to F
    private static final String[] LETTER_GRADES = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
    // scores corresponding to each letter grade
    private static final double[] SCORES        = {4.33, 4.00, 3.67, 3.33, 3.00, 2.67, 2.33, 2.00, 1.67, 1.00, 0.00};
    // number of grades
    int    n     = 0;
    // current total scores
    double total = 0.0;
    // a symbol table mapping letter grades to numerical scores
    private BinarySearchST<String, Double> st;

    /**
     * Initialize symbol with letter grades and corresponding scores
     */
    public GPACalculator() {
        st = new BinarySearchST<>(11);
        for (int i = 0; i < LETTER_GRADES.length; i++)
            st.put(LETTER_GRADES[i], SCORES[i]);
    }

    /********************************************************************************************************
     * Test routing
     ********************************************************************************************************/

    public static void main(String[] args) {
        GPACalculator gpa = new GPACalculator();
        while (!StdIn.isEmpty()) {
            String letter = StdIn.readString();
            gpa.addGrade(letter);
        }
        StdOut.printf("Average GPA: %.2f\n", gpa.calGPA());
    }

    /**
     * add a new letter grade into total
     *
     * @param letter new letter grade
     */
    public void addGrade(String letter) {
        if (!st.contains(letter)) throw new IllegalArgumentException("Invalid letter grade");
        total += st.get(letter);
        n++;
    }

    /**
     * Calculate and return the current GPA
     *
     * @return the current GPA
     */
    public double calGPA() {
        if (n == 0) return 0.0;
        return total / n;
    }
}

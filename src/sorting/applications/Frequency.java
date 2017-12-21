package sorting.applications;

import libs.StdIn;
import libs.StdOut;

import java.util.Arrays;

/**
 * Reads strings from standard input and prints the number of
 * times each string occurs, in descending order of frequency
 */
public class Frequency {
    public static void main(String[] args) {
        // Read the input
        String[] a = StdIn.readAllStrings();
        // Show frequency
        showFrequency(a);
    }

    private static void showFrequency(String[] a) {
        // Sort the input
        Arrays.sort(a);
        int n = a.length;

        // Create an Array of distinct string and their frequencies
        Record[] records = new Record[n];
        String word = a[0];
        int freq = 1;
        int j = 0;
        for (int i = 1; i < n; i++) {
            if (!a[i].equals(word)) {
                records[j++] = new Record(word, freq);
                word = a[i];
                freq = 0;
            }
            freq++;
        }
        records[j++] = new Record(word, freq);
        Arrays.sort(records, 0, j);

        for (int i = j - 1; i >= 0; i--)
            StdOut.println(records[i]);
    }
}

package sorting.applications;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Return the objects in a[] in sorted order, with duplicates removed.
 */
public class Dedup {
    public static void main(String[] args) {
        // Read strings from command line
        String[] a = In.readStrings();
        // Remove duplicates in the array
        String[] result = dedup(a);
        // Show result without duplicates
        for (String r : result)
            StdOut.print(r + " ");
        StdOut.println();
    }

    /**
     * Return the objects in a[] in sorted order, with duplicates removed.
     *
     * @param a
     *
     * @return the objects in a[] in sorted order, with duplicates removed.
     */
    public static String[] dedup(String[] a) {
        // Keeping unique items
        String[] tmp = new String[a.length];
        // Sort the array
        Arrays.sort(a);
        // unique array is empty
        int i = 0;
        tmp[0] = a[0];

        // Iterate over strings
        for (int j = 1; j < a.length; j++) {
            // skip when element exist
            if (a[j].equals(tmp[i])) continue;
            // add unique element into array
            tmp[++i] = a[j];
        }
        // Intercept results in tmp[0..i]
        String[] result = new String[i + 1];
        for (int j = 0; j <= i; j++)
            result[j] = tmp[j];
        // Return array without duplicates
        return result;
    }
}

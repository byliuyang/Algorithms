package sorting.applications;

import libs.StdIn;
import libs.StdOut;

import java.util.Arrays;

/**
 * Compilation: javac Domain.java
 * Execution: java Domain
 * Dependencies:
 *
 * Reads domain names from standard input and prints the reverse
 * domains in sorted order
 */
public class Domain implements Comparable<Domain> {
    private String[] fields;
    private int      n;

    public Domain(String domain) {
        fields = domain.split("\\.");
        n = fields.length;
    }

    public static void main(String[] args) {
        String[] names = StdIn.readAllStrings();
        Domain[] domains = new Domain[names.length];

        for (int i = 0; i < domains.length; i++)
            domains[i] = new Domain(names[i]);

        Arrays.sort(domains);

        for (int i = 0; i < domains.length; i++)
            StdOut.println(domains[i]);
    }

    @Override
    public int compareTo(Domain that) {
        for (int i = 0; i < Math.min(this.n, that.n); i++) {
            String s = this.fields[this.n - i - 1];
            String t = that.fields[that.n - i - 1];
            int c = s.compareTo(t);
            if (c < 0) return -1;
            else if (c > 0) return 1;
        }
        return this.n - that.n;
    }

    @Override
    public String toString() {
        if (n == 0) return "";
        String s = fields[0];

        for (int i = 1; i < n; i++)
            s = fields[i] + "." + s;
        return s;
    }
}

package sorting.applications;

import libs.StdIn;
import libs.StdOut;
import sorting.priorityQueues.MinPQ;

/**
 * A data type Version that represents a software version number,
 * such as 115.1.1, 115.10.1, 115.10.2
 */
public class Version implements Comparable<Version> {
    private final int major;
    private final int minor;
    private final int build;

    public Version(String version) {
        String[] s = version.split("\\.");
        if (s.length < 3) throw new IllegalArgumentException();

        this.major = Integer.parseInt(s[0]);
        this.minor = Integer.parseInt(s[1]);
        this.build = Integer.parseInt(s[2]);
    }

    public static void main(String[] args) {
        MinPQ<Version> pq = new MinPQ<>(100);
        while (StdIn.hasNextLine()) pq.insert(new Version(StdIn.readLine()));
        while (!pq.isEmpty()) StdOut.println(pq.delMin());
    }

    @Override
    public int compareTo(Version that) {
        if (this.major < that.major) return -1;
        if (this.major > that.major) return 1;
        if (this.minor < that.minor) return -1;
        if (this.minor > that.minor) return 1;
        if (this.build < that.build) return -1;
        if (this.build > that.build) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + build;
    }
}

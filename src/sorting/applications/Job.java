package sorting.applications;

import libs.StdOut;
import libs.StdRandom;

/**************************************************************************
 *
 * Compilation: javac Job.java
 * Execution: java Job n
 * Dependencies: StdOut.java StdRandom.java
 *
 * A data type that implements processing time
 *
 **************************************************************************/

public class Job implements Comparable<Job> {
    private final String name;
    private final double time;

    public Job(String name, double time) {
        if (time < 0) throw new RuntimeException("Can't have negative processing time");
        this.name = name;
        this.time = time;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            double time = StdRandom.uniform(0, 1000);
            StdOut.println(new Job("Job-" + i, time));
        }
    }

    @Override
    public int compareTo(Job that) {
        return Double.compare(this.time, that.time);
    }

    @Override
    public String toString() {
        return String.format("%-8s %.1f", name, time);
    }

    public double getTime() {
        return time;
    }
}

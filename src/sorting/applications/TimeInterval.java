package sorting.applications;

import libs.StdOut;
import libs.StdRandom;

class TimeInterval implements Comparable<TimeInterval> {
    private final int start;
    private final int finish;

    public TimeInterval(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            int start = StdRandom.uniform(0, 22);
            int finish = StdRandom.uniform(start + 1, 24);
            StdOut.println(new TimeInterval(start, finish));
        }
    }

    @Override
    public int compareTo(TimeInterval that) {
        if (this.start < that.start) return -1;
        if (this.start > that.start) return 1;
        return 0;
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }

    @Override
    public String toString() {
        return start + " " + finish;
    }
}
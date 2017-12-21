package sorting.applications;

import libs.StdIn;

import java.util.Arrays;

/**
 * Created by harryliu on 8/1/16.
 */
public class IdleTime {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        TimeInterval[] jobs = new TimeInterval[n];
        for (int i = 0; i < n; i++) {
            int start = StdIn.readInt();
            int finish = StdIn.readInt();
            jobs[i] = new TimeInterval(start, finish);
        }

        Arrays.sort(jobs);


    }
}

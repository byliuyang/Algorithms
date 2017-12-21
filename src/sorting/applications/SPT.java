package sorting.applications;

import libs.StdIn;
import libs.StdOut;

import java.util.Arrays;

/**************************************************************************************
 *
 * Compilation: javac SPT.java
 * Execution: java SPT
 * Dependencies: Job.java
 *
 * Given set of jobs and processing times, find a schedule
 * that minimizes the average completion time of jobs ( Shortest processing time).
 *
 /*************************************************************************************/

public class SPT {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        // sort jobs in ascending order of processing time
        Arrays.sort(jobs);

        // print out schedule
        for (int i = 0; i < n; i++)
            StdOut.println(jobs[i]);
    }
}

package sorting.applications;

import libs.StdIn;
import libs.StdOut;
import sorting.priorityQueues.MinPQ;

import java.util.Arrays;

/**************************************************************************
 *
 * Compilation: javac LPT.java
 * Execution: java LPT m < jobs.txt
 * Dependencies:
 *
 * Find proximate solution to the load balancing
 * problem using LPT (Longest processing time first) rule.
 *
 **************************************************************************/

public class LPT {
    public static void main(String[] args) {
        // number of processors
        int m = Integer.parseInt(args[0]);
        int n = StdIn.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }
        // sort jobs in ascending order of processing time
        Arrays.sort(jobs);
        // Generate n processors and put on priority queue
        MinPQ<Processor> pq = new MinPQ<>(m);
        for (int i = 0; i < m; i++)
            pq.insert(new Processor());

        // assign each job (in descending order of time) to process that is least busy
        for (int i = n - 1; i >= 0; i--) {
            Processor min = pq.delMin();
            min.add(jobs[i]);
            pq.insert(min);
        }

        // print out contents of each processor
        while (!pq.isEmpty()) StdOut.println(pq.delMin());
    }
}

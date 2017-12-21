package sorting.applications;

import fundamentals.bagQueueStack.queue.Queue;

/**************************************************************************
 *
 * Compilation: javac Processor.java
 * Execution: none
 * Dependencies: Queue.java
 *
 * Processor data type represents a processor with list of
 * jobs, each of integer length. Its load is total amount
 * of processing time.
 *
 **************************************************************************/

public class Processor implements Comparable<Processor> {
    private double     load = 0;
    private Queue<Job> list = new Queue<>();

    public void add(Job job) {
        list.enqueue(job);
        load += job.getTime();
    }

    @Override
    public int compareTo(Processor that) {
        return Double.compare(this.load, that.load);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(load + ": ");
        for (Job job : list)
            s.append(job + "      ");
        return s.toString();
    }
}

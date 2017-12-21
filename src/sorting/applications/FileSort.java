package sorting.applications;

import libs.StdIn;
import libs.StdOut;
import sorting.priorityQueues.MinPQ;

/************************************************************************************
 *
 * Compilation: javac FileSort.java
 * Execution: java FileSort
 * Dependencies:
 *
 * A data type that allow to write a client that can sort a file such as
 *
 * 1-Oct-28     3500000
 * 2-Oct-28     3850000
 * 3-Oct-28     4060000
 * 4-Oct-28     4330000
 * 5-Oct-28     4360000
 * 30-Dec-99  554680000
 * 31-Dec-99  554680000
 *
 ************************************************************************************/
public class FileSort {

    public static void main(String[] args) {
        int n = StdIn.readInt();
        StdOut.println(StdIn.readLine());
        MinPQ<FileInfo> pq = new MinPQ<>(n);
        while (StdIn.hasNextLine()) pq.insert(new FileInfo(StdIn.readLine()));
        while (!pq.isEmpty()) StdOut.println(pq.delMin());
    }
}

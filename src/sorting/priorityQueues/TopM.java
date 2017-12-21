package sorting.priorityQueues;

import fundamentals.bagQueueStack.stack.Stack;
import fundamentals.dataAbstraction.Transaction;
import libs.StdIn;
import libs.StdOut;

/**
 * A priority queue client
 */
public class TopM {
    public static void main(String[] args) {
        // Print the top M lines in the input stream.
        int M = Integer.parseInt(args[0]);

        MinPQ<Transaction> pq = new MinPQ<>(M + 1);

        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on PQ
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                // Remove minimum if M+1 entries on the PQ
                pq.delMin();
        }
        // Top M entries are on the PQ
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);
    }
}

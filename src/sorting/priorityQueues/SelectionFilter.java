package sorting.priorityQueues;

import fundamentals.bagQueueStack.stack.Stack;
import libs.StdIn;
import libs.StdOut;

/********************************************************************
 *
 * Compilation: javac SelectionFilter.java
 * Execution: java SelectionFilter
 * Dependencies: StdIn.java StdOut.java
 *
 * A TopM client that reads points (x, y, z) from
 * standard input, takes a value M from the command
 * line, and prints the M points that are closest to
 * the origin in Euclidean distance
 *
 ********************************************************************/

public class SelectionFilter {

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);

        MaxPQ<Point3D> pq = new MaxPQ<>(M + 1);

        while (StdIn.hasNextLine()) {
            pq.insert(new Point3D(StdIn.readLine()));
            if (pq.size() > M) pq.delMax();
        }

        Stack<Point3D> stack = new Stack<>();
        while (!pq.isEmpty()) stack.push(pq.delMax());
        for (Point3D point3D : stack) StdOut.println(point3D);
    }

    /********************************************************************
     * Helper ADT
     ********************************************************************/

    private static class Point3D implements Comparable<Point3D> {
        int    x;
        int    y;
        int    z;
        double d;

        public Point3D(String point3D) {
            String[] coor = point3D.split("\\s");

            if (coor.length != 3) throw new IllegalArgumentException();

            x = Integer.parseInt(coor[0]);
            y = Integer.parseInt(coor[1]);
            z = Integer.parseInt(coor[2]);

            d = Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Point3D that) {
            if (this.d < that.d) return -1;
            if (this.d > that.d) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ") Euclidean Distance = " + d;
        }
    }
}

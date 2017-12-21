package fundamentals.unionFind;

import fundamentals.bagQueueStack.bag.RandomBag;
import libs.StdOut;

/**
 * Generate all the connections in an N-by-N grid,
 * puts them in random order,randomly orients them
 */
public class RandomGrid {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        RandomGrid randomGrid = new RandomGrid();
        Connection[] connections = randomGrid.generate(N);

        for (Connection connection : connections) {
            StdOut.printf("%d %d\n", connection.p, connection.q);
        }
    }

    public Connection[] generate(int N) {
        RandomBag<Connection> bag = new RandomBag<>(); // bag of connections

        // put all generated connection into bag
        for (int y = 0; y < N; y++)
            for (int x = 0; x < N; x++) {

                // The direct neighbors for current
                int left = Math.max(0, x - 1);
                int right = Math.min(x + 1, N - 1);
                int top = Math.max(0, y - 1);
                int bottom = Math.min(y + 1, N - 1);

                Index current = new Index(x, y, N);

                // Add connection between current site and its neighbors
                Index leftIndex = new Index(left, y, N);
                bag.add(new Connection(leftIndex.i, current.i));
                bag.add(new Connection(current.i, leftIndex.i));

                Index rightIndex = new Index(right, y, N);
                bag.add(new Connection(rightIndex.i, current.i));
                bag.add(new Connection(current.i, rightIndex.i));

                Index topIndex = new Index(x, top, N);
                bag.add(new Connection(topIndex.i, current.i));
                bag.add(new Connection(current.i, topIndex.i));

                Index bottomIndex = new Index(x, bottom, N);
                bag.add(new Connection(bottomIndex.i, current.i));
                bag.add(new Connection(current.i, bottomIndex.i));
            }

        // iterate all connections in random order
        Connection[] connections = new Connection[bag.size()]; // Random ordered connections

        int i = 0;
        for (Connection connection : bag) {
            connections[i] = connection;
            i++;
        }

        return connections;
    }
}

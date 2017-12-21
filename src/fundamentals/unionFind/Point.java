package fundamentals.unionFind;

/**
 * Created by harryliu on 7/9/16.
 */
public class Point {
    public int x;
    public int y;

    public Point(int i, int N) {
        // Convert array index to point
        x = i % N;
        y = i / N;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
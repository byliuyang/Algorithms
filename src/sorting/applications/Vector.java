package sorting.applications;

/**
 * A data type for sorting multidimensional vectors of d integers,
 * putting the vectors in order by first component,
 * those with equal first component in order by second component,
 * those with equal first and second components in order by third component,
 * and so forth.
 */
public class Vector implements Comparable<Vector> {
    private final double[] components;
    private final int      n;

    public Vector(double[] components) {
        this.components = components;
        this.n = components.length;
    }

    @Override
    public int compareTo(Vector that) {
        int m = Math.min(this.n, that.n);
        for (int i = 0; i < m; i++) {
            int cmp = Double.compare(this.components[i], that.components[i]);
            if (cmp < 0) return -1;
            else if (cmp > 0) return 1;
        }
        return this.n - that.n;
    }
}

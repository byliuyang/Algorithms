package sorting.elementarySorts.animations;

/**
 * RandomDoubleAnimation for insertion sort
 */
public class SelectionAnimation extends RandomDoubleAnimation {

    public static void main(String[] args) {
        // number of Doubles
        int N = Integer.parseInt(args[0]);

        // Generate N Doubles
        Double[] a = randomDoubles(N);

        // Initialize canvas
        initCanvas(a);

        // Show the array before sort
        show(a, -1, 0, -1);
        // Sort the array
        sort(a);
        // Show the result
        show(a, N, -1, -1);
    }

    public static void sort(Double[] a) {
        // Sort a[] into increasing order
        int N = a.length; // array length
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with smallest entry in a[i+1, N).
            int min = i; // Index of minimal entry.
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
            // Show min and i
            show(a, i, N - 1, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

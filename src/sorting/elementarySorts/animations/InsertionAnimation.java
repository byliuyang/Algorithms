package sorting.elementarySorts.animations;

/**
 * RandomDoubleAnimation for insertion sort
 */
public class InsertionAnimation extends RandomDoubleAnimation {

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
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // Insert a[i] among a[i - 1], a[i - 2], a[i - 3]...
            int k = i; // Keep track final position for i
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
                k = j - 1;
            }

            show(a, k + 1, i, k);
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

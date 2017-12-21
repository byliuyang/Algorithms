package fundamentals.analysis;

import libs.StdOut;

/**
 * Find a local minimum: a pair of indices i and j such that
 * a[i][j] < a[i+1][j],
 * a[i][j] < a[i-1][j],
 * * a[i][j] < a[i][j+1],
 * and a[i][j] < a[i][j-1]
 */
public class MatrixLocalMin {
    public static void main(String[] args) {
        int xlo = Integer.parseInt(args[0]);
        int xhi = Integer.parseInt(args[1]);

        int ylo = Integer.parseInt(args[2]);
        int yhi = Integer.parseInt(args[3]);

        int xN = xhi - xlo + 1;
        int yN = yhi - ylo + 1;

        int[][] m = new int[xN][yN];

        // Generate quadratic surface
        for (int i = 0; i < xN; i++)

            for (int j = 0; j < yN; j++)

                m[i][j] = (int) (Math.pow(xlo + i, 2) + Math.pow(ylo + j, 2));

        int[] coor = localMin(m);
        StdOut.printf("(%d, %d)\n", coor[0], coor[1]);
    }

    public static int[] localMin(int[][] m) {
        int iN = m.length;
        int jN = m[0].length;

        int il = 0;
        int ir = iN - 1;
        int i = (il + ir) / 2;

        int jl = 0;
        int jr = jN - 1;
        int j = (jl + jr) / 2;

        while (il <= ir && jl <= jr && i - 1 >= 0 && i + 1 < iN && j - 1 >= 0 && j + 1 < jN) {

            if (m[i][j] <= m[i - 1][j] && m[i][j] <= m[i + 1][j] && m[i][j] <= m[i][j - 1] && m[i][j] <= m[i][j + 1])
                return new int[]{i, j};

            if (m[i - 1][j] < m[i + 1][j]) ir = i - 1;
            else if (m[i - 1][j] > m[i + 1][j]) il = i + 1;

            if (m[i][j - 1] < m[i][j + 1]) jr = j - 1;
            else if (m[i][j - 1] > m[i][j + 1]) jl = j + 1;

            i = (il + ir) / 2;
            j = (jl + jr) / 2;

        }

        return new int[]{-1, -1};
    }
}

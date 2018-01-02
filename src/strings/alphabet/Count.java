package strings.alphabet;

import libs.StdIn;
import libs.StdOut;

public class Count {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet(args[0]);
        int R = alphabet.radix();

        int[] count = new int[R];

        String s = StdIn.readAll();
        int N = s.length();

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (alphabet.contains(c))
                count[alphabet.toIndex(c)]++;
        }

        for (int i = 0; i < R; i++)
            StdOut.println(alphabet.toChar(i) + " " + count[i]);
    }
}

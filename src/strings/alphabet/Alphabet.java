package strings.alphabet;

import libs.StdOut;

public class Alphabet {
    private int[] indices;
    private char[] alphabet;
    private final int R;

    public static final Alphabet BINARY = new Alphabet("01");

    public static final Alphabet OCTAL = new Alphabet("01234567");

    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    public static final Alphabet HEXDECIMAL = new Alphabet("01234567ABCDEF");

    public static final Alphabet DNA = new Alphabet("ACGT");

    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    public static final Alphabet ASCII = new Alphabet(128);

    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    public static final Alphabet UNICODE16 = new Alphabet(65536);


    public Alphabet(String s) {
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (unicode[c])
                throw new IllegalArgumentException("Illegal alphabet: repeated characters = '" + c + 'c');
            unicode[c] = true;
        }
        alphabet = s.toCharArray();
        R = alphabet.length;

        indices = new int[Character.MAX_VALUE];
        for (int i = 0; i < indices.length; i++)
            indices[i] = -1;
        for (int c = 0; c < R; c++)
            indices[alphabet[c]] = c;
    }

    public Alphabet() {
        this(256);
    }

    private Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        indices = new int[R];

        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;

        for (int i = 0; i < R; i++)
            indices[i] = i;
    }

    public char toChar(int index) {
        return alphabet[index];
    }

    public int toIndex(char c) {
        if (c >= indices.length || indices[c] == -1)
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        return indices[c];
    }

    public boolean contains(char c) {
        return indices[c] != -1;
    }

    public int radix() {
        return R;
    }

    public int lgR() {
        int lgR = 0;
        for (int t = R - 1; t >= 1; t = t >>> 1)
            lgR++;
        return lgR;
    }

    public int[] toIndices(String s) {
        char[] arr = s.toCharArray();
        int[] charIndices = new int[arr.length];
        for (int i = 0; i < charIndices.length; i++)
            charIndices[i] = toIndex(arr[i]);
        return charIndices;
    }

    public String toChars(int[] indices) {
        StringBuilder builder = new StringBuilder();
        for (int index : indices)
            builder.append(toChar(index));
        return builder.toString();
    }

    public static void main(String[] args) {
        int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[]  encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[]  encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }
}

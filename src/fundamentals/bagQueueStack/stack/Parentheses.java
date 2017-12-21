package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * Read in a text stream and determine whether its
 * parentheses are properly balanced.
 */
public class Parentheses {
    public static void main(String[] args) {
        StdOut.println(matchParentheses());
    }

    public static boolean matchParentheses() {
        Stack<String> lps = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            if (s.equals("(") || s.equals("[") || s.equals("{"))
                // push onto stack when get left parentheses
                lps.push(s);
            else {
                if (s.equals(")") && !lps.pop().equals("(")) return false;
                else if (s.equals("]") && !lps.pop().equals("[")) return false;
                else if (s.equals("}") && !lps.pop().equals("{")) return false;
            }
        }

        return true;
    }
}

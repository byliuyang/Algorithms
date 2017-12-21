package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * Insert left parentheses into expression at proper location
 */
public class Infix {
    public static void main(String[] args) {
        Stack<String> p = new Stack<>();
        Stack<String> s = new Stack<>();
        Stack<String> r = new Stack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            // save the expression
            s.push(item);
            // push appropriate number of "(" on to stack
            if (item.equals(")")) p.push("(");
        }

        int N = s.size();
        for (int i = 0; i < N; i++) {
            String item = s.pop();
            if (item.equals("*") || item.equals("/") || item.equals("%")) r.push(p.pop());
            r.push(item);
        }

        N = p.size();
        for (int i = 0; i < N; i++) r.push(p.pop());

        for (String item : r) {
            StdOut.print(item + " ");
        }
    }
}

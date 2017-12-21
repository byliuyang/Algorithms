package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * Takes a postfix expression from standard input,
 * evaluates it, and prints the value
 */
public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("sqrt") || s.equals("%"))
                ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                Double val = vals.pop();

                if (op.equals("+")) val = vals.pop() + val;
                else if (op.equals("-")) val = vals.pop() - val;
                else if (op.equals("*")) val = vals.pop() * val;
                else if (op.equals("/")) val = vals.pop() / val;
                else if (op.equals("sqrt")) val = Math.sqrt(val);
                else if (op.equals("%")) val = vals.pop() % val;

                vals.push(val);
            } else {
                vals.push(Double.parseDouble(s));
            }
        }

        StdOut.println(vals.pop());
    }
}

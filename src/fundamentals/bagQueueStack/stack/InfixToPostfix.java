package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * Converts an arithmetic expression from
 * infix to postfix
 */
public class InfixToPostfix {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("(")) StdOut.print(item + " ");
        }
    }
}

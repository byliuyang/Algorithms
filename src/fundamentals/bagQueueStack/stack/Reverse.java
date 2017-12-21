package fundamentals.bagQueueStack.stack;

import libs.StdIn;
import libs.StdOut;

/**
 * Created by harryliu on 7/1/16.
 */
public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty()) stack.push(StdIn.readInt());

        for (int i : stack)
            StdOut.println(i);
    }
}

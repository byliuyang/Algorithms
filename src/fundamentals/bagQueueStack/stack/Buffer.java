package fundamentals.bagQueueStack.stack;

/**
 * A buffer in a text editor
 */
public class Buffer {

    private Stack<Character> leftBuffer;
    private Stack<Character> rightBuffer;

    /**
     * create an empty buffer
     */
    Buffer() {
        leftBuffer = new Stack<>();
        rightBuffer = new Stack<>();
    }

    /**
     * insert c at the cursor position
     *
     * @param c
     */
    void insert(char c) {
        leftBuffer.push(c);
    }

    /**
     * delete and return the character at the cursor
     *
     * @return
     */
    char delete() {
        return leftBuffer.pop();
    }

    /**
     * move the cursor k positions to the left
     *
     * @param k
     */
    void left(int k) {
        rightBuffer.push(leftBuffer.pop());
    }

    /**
     * move the cursor k positions to the right
     *
     * @param k
     */
    void right(int k) {
        leftBuffer.push(rightBuffer.pop());
    }

    /**
     * number of characters in the buffer
     *
     * @return
     */
    int size() {
        return leftBuffer.size() + rightBuffer.size();
    }
}

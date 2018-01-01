package graphs.directedGraphs;

import fundamentals.bagQueueStack.queue.Queue;
import fundamentals.bagQueueStack.stack.Stack;
import libs.In;
import libs.StdOut;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for (int source = 0; source < digraph.V(); source++)
            if (!marked[source]) dfs(digraph, source);
    }

    private void dfs(Digraph digraph, int vertex) {
        pre.enqueue(vertex);

        marked[vertex] = true;
        for (int adjacent : digraph.adj(vertex))
            if (!marked[adjacent])
                dfs(digraph, adjacent);

        post.enqueue(vertex);
        reversePost.push(vertex);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);

        for (int v : depthFirstOrder.pre())
            StdOut.print(v + " ");
        StdOut.println();

        for (int v : depthFirstOrder.post())
            StdOut.print(v + " ");
        StdOut.println();

        for (int v : depthFirstOrder.reversePost())
            StdOut.print(v + " ");
        StdOut.println();
    }
}

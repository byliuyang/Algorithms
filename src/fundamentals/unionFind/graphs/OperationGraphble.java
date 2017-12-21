package fundamentals.unionFind.graphs;

/**
 * Created by harryliu on 7/9/16.
 */
public class OperationGraphble {

    // For graphs
    private int cost  = 0;
    private int total = 0;
    private int i     = 0;

    public void clear() {
        // For Stats
        cost = 0;
    }

    public int getCostOp() {
        return cost;
    }

    public int getAvgOp() {
        return total / i;
    }

    public void incrementCost() {
        cost++;
    }

    public void incrementTotal() {
        total++;
    }

    public void incrementI() {
        i++;
    }
}

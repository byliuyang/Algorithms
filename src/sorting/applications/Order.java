package sorting.applications;

import libs.StdOut;
import libs.StdRandom;

/*************************************************************************************
 *
 * Compilation: javac Order.java
 * Execution: java Order n
 * Dependencies: StdRandom.java StdOut.java
 *
 * A data type represent orders in stock market,
 * specifying a maximum buy or minimum sell price,
 * and how many shares to trade at that price.
 *
 * % java Order 10
 *
 * 10
 * 87.85  5433845 BUY
 * 52.40  3793509 SELL
 * 66.93   705253 SELL
 * 61.51  4707815 BUY
 * 7.24    780925 SELL
 * 80.54  7326115 BUY
 * 56.28   576363 SELL
 * 16.41   917284 BUY
 * 71.06  6935907 BUY
 * 74.34  3719948 SELL
 *
 *************************************************************************************/

public class Order implements Comparable<Order> {
    public static final String ORDER_TYPE_BUY  = "BUY";
    public static final String ORDER_TYPE_SELL = "SELL";
    private final double price;
    private final int    shares;

    public Order(double price, int shares) {
        this.price = price;
        this.shares = shares;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // Generate n orders
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            double price = StdRandom.uniform(0.0, 100.0);
            int shares = StdRandom.uniform(0, 10000000);
            String type = StdRandom.uniform() > 0.5 ? Order.ORDER_TYPE_BUY : Order.ORDER_TYPE_SELL;
            StdOut.println(new Order(price, shares) + " " + type);
        }
    }

    @Override
    public String toString() {
        return String.format("%-5.2f %8d", price, shares);
    }

    @Override
    public int compareTo(Order that) {
        return Double.compare(this.price, that.price);
    }

    public int shares() {
        return shares;
    }

    public double price() {
        return price;
    }
}
package sorting.applications;

import libs.StdIn;
import libs.StdOut;
import sorting.priorityQueues.MaxPQ;
import sorting.priorityQueues.MinPQ;

/*************************************************************************************
 *
 * Compilation: javac StockTrading.java
 * Execution: java StockTrading
 * Dependencies: MaxPQ.java MinPQ.java StdOut.java StdIn.java
 *
 * Match up buyers and sellers in stock market and test it through simulation.
 *
 * % java StockTrading < order.txt
 *
 * 4687829 shares at price = 86.16 sold
 * 2596947 shares at price = 86.16 sold
 * 890862 shares at price = 60.60 sold
 * 4447628 shares at price = 15.13 bought
 * 931404 shares at price = 15.13 bought
 * ......
 *
 * 1781148 shares at price = 61.31 bought
 * 3271582 shares at price = 63.96 bought
 * 389912 shares at price = 52.11 sold
 * 2963964 shares at price = 48.00 sold
 * 409467 shares at price = 28.00 sold
 * 1346315 shares at price = 23.87 sold
 *
 *************************************************************************************/

public class StockTrading {
    // keep orders from buyers
    private MaxPQ<Order> buyers;
    // Keep orders from sellers
    private MinPQ<Order> sellers;

    public StockTrading(int b, int s) {
        this.buyers = new MaxPQ<>(b);
        this.sellers = new MinPQ<>(s);
    }

    /*************************************************************************************
     * Stock market simulation
     *************************************************************************************/

    public static void main(String[] args) {
        // number of orders
        int n = StdIn.readInt();

        StockTrading trading = new StockTrading(n, n);

        while (StdIn.hasNextLine()) {
            double price = StdIn.readDouble();
            int shares = StdIn.readInt();
            // Create order
            Order order = new Order(price, shares);
            String orderType = StdIn.readString();
            // buying
            if (orderType.equals(Order.ORDER_TYPE_BUY)) trading.addBuyer(order);
                // selling
            else if (orderType.equals(Order.ORDER_TYPE_SELL)) trading.addSeller(order);
        }
    }

    /**
     * Add buying order and match it with existing order or orders
     *
     * @param buy buying order
     */
    public void addBuyer(Order buy) {
        while (!this.sellers.isEmpty()) {
            // Prefer cheapest prices
            Order selling = this.sellers.delMin();
            // prices match
            if (less(selling, buy)) {
                // Trading
                int sellShares = selling.shares();
                int buyShares = buy.shares();
                StdOut.printf("%8d shares at price = %-5.2f bought\n", Math.min(sellShares, buyShares), selling.price());
                int diff = buyShares - sellShares;
                // shortage
                if (diff < 0) {
                    sellers.insert(new Order(selling.price(), -diff));
                    return;
                }
                // surplus
                else if (diff > 0) buy = new Order(buy.price(), diff);
                else return;

            } else {
                // prices too high
                sellers.insert(selling);
                break;
            }
        }
        // No matched sellers
        buyers.insert(buy);
    }

    /*************************************************************************************
     * General helper functions
     *************************************************************************************/

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void addSeller(Order sell) {
        while (!this.buyers.isEmpty()) {
            // Prefer most profits
            Order buying = this.buyers.delMax();
            // prices match
            if (less(sell, buying)) {
                // Trading
                int sellShares = sell.shares();
                int buyShares = buying.shares();
                StdOut.printf("%8d shares at price = %-5.2f sold \n", Math.min(sellShares, buyShares), buying.price());
                int diff = sellShares - buyShares;
                // surplus
                if (diff < 0) {
                    buyers.insert(new Order(buying.price(), -diff));
                    return;
                }
                // shortage
                else if (diff > 0) sell = new Order(sell.price(), diff);
                else return;

            } else {
                // prices too high
                buyers.insert(buying);
                break;
            }
        }
        // No matched buyers
        sellers.insert(sell);
    }
}

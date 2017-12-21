package sorting.applications;

import java.util.Comparator;
import java.util.Date;

/**
 * CmpTransaction
 */
public class CmpTransaction {
    private String who;    // customer
    private Date   when;   // date
    private double amount; // amount

    public CmpTransaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinity");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public CmpTransaction(String transaction) {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinity");
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        CmpTransaction transaction = (CmpTransaction) that;
        return this.who.equals(transaction.who) && when.equals(transaction.when) && this.amount == transaction.amount;
    }

    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public static class WhoOrder implements Comparator<CmpTransaction> {
        @Override
        public int compare(CmpTransaction v, CmpTransaction w) {
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<CmpTransaction> {
        @Override
        public int compare(CmpTransaction v, CmpTransaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<CmpTransaction> {
        @Override
        public int compare(CmpTransaction v, CmpTransaction w) {
            if (v.amount < w.amount) return -1;
            if (v.amount > w.amount) return 1;
            return 0;
        }
    }
}

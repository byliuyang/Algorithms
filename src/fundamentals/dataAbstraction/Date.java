package fundamentals.dataAbstraction;

/**
 * Date
 */
public class Date implements Comparable<Date> {
    private static final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int month; // month (between 1 and 12)
    private final int day;   // day (between 1 and DAYS[m])
    private final int year;  // year

    public Date(String date) {
        String[] fields = date.split("/");

        if (fields.length != 3) throw new IllegalArgumentException("Invalid date");

        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);

        if (!isValid(month, day, year)) throw new IllegalArgumentException("Invalid date");
    }

    private boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12) return false;
        if (d < 1 || d > DAYS[m]) return false;
        return !(m == 2 && d == 29 && !isLeapYear(y));
    }

    private boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Date date = (Date) x;
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    @Override
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return 1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return 1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return 1;
        return 0;
    }
}

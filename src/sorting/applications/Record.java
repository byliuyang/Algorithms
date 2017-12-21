package sorting.applications;

/**
 * Created by harryliu on 8/1/16.
 */
public class Record implements Comparable<Record> {
    private final String name;
    private final long   val;

    public Record(String name, long val) {
        this.name = name;
        this.val = val;
    }

    @Override
    public int compareTo(Record that) {
        if (this.val < that.val) return -1;
        else if (this.val > that.val) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%6d %s", val, name);
    }
}

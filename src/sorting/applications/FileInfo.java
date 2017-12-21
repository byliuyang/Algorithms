package sorting.applications;

import libs.StdOut;
import libs.StdRandom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**************************************************************************
 *
 * Compilation: javac FileInfo.java
 * Execution: java FileInfo n
 * Dependencies: StdRandom.java StdOut.java
 *
 * A data type that implements file information
 *
 **************************************************************************/

public class FileInfo implements Comparable<FileInfo> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
    private long size;
    private Date lastModified;

    public FileInfo(String file) {
        String[] info = file.split("\\s+");
        if (info.length < 2) throw new IllegalArgumentException();

        try {
            this.lastModified = dateFormat.parse(info[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.size = Long.parseLong(info[1]);
    }

    public FileInfo(long size, Date lastModified) {
        this.size = size;
        this.lastModified = lastModified;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            long size = StdRandom.uniform(100000);
            int year = StdRandom.uniform(2010, 2014);
            int month = StdRandom.uniform(0, 12);
            int day = StdRandom.uniform(0, 29);
            StdOut.println(new FileInfo(size, new Date(year, month, day)));
        }
    }

    @Override
    public String toString() {
        return String.format("%9s %10d", dateFormat.format(lastModified), size);
    }

    @Override
    public int compareTo(FileInfo that) {
        if (this.size < that.size) return -1;
        if (this.size > that.size) return 1;
        return this.lastModified.compareTo(that.lastModified);
    }
}
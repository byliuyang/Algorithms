package sorting.applications;

import libs.StdOut;

import java.io.File;
import java.util.Arrays;

/*************************************************************************************
 *
 * Compilation: javac FileSorter.java
 * Execution: java FileSorter directory
 * Dependencies:
 *
 * Takes the name of a directory as a command-line argument and prints out
 * all of the files in the current directory, sorted by file name.
 *
 *************************************************************************************/

public class FileSorter {
    public static void main(String[] args) {
        String path = args[0];
        File file = new File(path);
        if (!file.exists()) throw new RuntimeException("Directory do not exist!");
        String[] names = file.list();
        if (names != null) {
            Arrays.sort(names);
            for (String name : names)
                StdOut.println(name);
        }
    }
}

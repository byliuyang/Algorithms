package fundamentals.bagQueueStack.stack;

import libs.StdOut;

import java.io.File;

/**
 * Listing files
 */
public class ListingFiles {
    private static final String TAB   = " ";
    private static final String EMPTY = "";

    public static void main(String[] args) {
        String pathName = args[0];

        Stack<File> fileStack = new Stack<>();

        Stack<String> tabStack = new Stack<>();

        File rootFile = new File(pathName);
        fileStack.push(rootFile);
        tabStack.push("");

        while (!fileStack.isEmpty()) {
            File file = fileStack.pop();
            String tab = tabStack.pop();

            StdOut.println(tab + file.getName());

            if (file.isDirectory()) {
                File[] files = file.listFiles();

                if (files != null) {
                    for (File f : files) {
                        fileStack.push(f);
                        tabStack.push(tab + TAB);
                    }
                }
            }
        }
    }
}

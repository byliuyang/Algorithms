package sorting.applications;

import libs.In;
import libs.StdOut;

import java.util.Arrays;

/**
 * Reads a list of words from standard input and prints all two-word
 * compound words in the list
 */
public class CompoundWord {

    public static void main(String[] args) {
        // Read words from command line
        String[] wordList = In.readStrings();
        // Sort the word list
        Arrays.sort(wordList);
        // Search each word in the list
        for (String word : wordList)
            // Print out the word if it is two-word compound
            if (isCompoundWord(word, wordList)) StdOut.println(word);
    }

    /**
     * Return <tt>true</tt> when <tt>word</tt> is compound word, <tt>false</tt> otherwise
     *
     * @param word     word to be testes
     * @param wordList list of word to search through
     *
     * @return <tt>true</tt> when <tt>word</tt> is compound word, <tt>false</tt> otherwise
     */
    public static boolean isCompoundWord(String word, String[] wordList) {
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            // prefix = word[0..i]
            String prefix = word.substring(0, i + 1);
            // search prefix in the word list
            if (Arrays.binarySearch(wordList, prefix) == -1) continue;
            // return false when no postfix
            if (wordLength == i + 1) return false;
            // postfix = word[i+1..wordLength]
            String postfix = word.substring(i + 1, wordLength);
            // return true when find postfix
            return Arrays.binarySearch(wordList, postfix) > -1;
        }

        return false;
    }
}

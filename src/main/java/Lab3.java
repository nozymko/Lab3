import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Lab3 {

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
    Scanner urlScanner;
    try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
    return "";
        }
    String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
    return contents;
    }


    //checks for a boundary at a given index
    //if there is a boundary, return true, else, return false
    public static boolean findBoundary(int index, String urlString) {
        char[] punctuation = {' ', ',', '.', '!', '?', '/', ')'};
        for (int i = 0; i < punctuation.length; i++) {
            if (urlString.length() == 0) {
                return false;
            }
            if (urlString.charAt(index) == (punctuation[i])) {
                return true;
            }
        }
        return false;
    }

    //finds the end boundary of the word to remove/add to count
    public static int findWord(String urlString) {
        int index = 0;
        while (findBoundary(index, urlString) == false) {
            if (index >= urlString.length()) {
                return -1;
            }
            index++;
        }
        return index;
    }

    //while there is a punctuation mark at the beginning of the string
    //remove that punctuation mark
    public static String trimPunctuation(String urlString) {
        if (urlString.length() > 0) {
            while (findBoundary(0, urlString)) {
                if (urlString.length() <= 0) {
                    break;
                }
                urlString = urlString.substring(1, urlString.length());
            }
        }
        return urlString;
    }

    public static int numWords(String urlString) {
        int numWords = 0;
        urlString += ' ';
        while (urlString.length() != 0) {
            urlString = urlString.substring(findWord(urlString), urlString.length());
            if (urlString.length() == 0) {
                break;
            }
            urlString = trimPunctuation(urlString);
            //System.out.println(urlString);
            numWords++;
        }
        return numWords;
    }

    public static void main(String[] args) {

        System.out.println(numWords(urlToString("https://www.bls.gov/tus/charts/chart9.txt")));

    }

}

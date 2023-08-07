package intro;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <a href="https://coursera.cs.princeton.edu/algs4/assignments/hello/specification.php">...</a>
 */
public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String champ = "";
        while (!StdIn.isEmpty()) {
            i++;
            String currentWord = StdIn.readString();
            if (StdRandom.bernoulli((double) 1/i)) {
                champ = currentWord;
            }
        }
        StdOut.println(champ);
    }
}

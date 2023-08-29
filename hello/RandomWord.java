/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int argc = 0;
        String result = "";
        String read;
        while (!StdIn.isEmpty()) {
            argc++;
            read = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / argc)) {
                result = read;
            }
        }
        System.out.println(result);
    }
}


/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        RandomizedQueue<String> str = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            str.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = str.iterator();
        for (int i = 0; i < size; i++) {
            System.out.println(iterator.next());
        }
    }
}


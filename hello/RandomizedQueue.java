/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INT_CAPACITY = 8;
    private Item[] items;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[INT_CAPACITY];
        n = 0;
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("null item");
        if (n == items.length) {
            resize(2 * items.length);
        }
        items[n] = item;
        n++;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("no");
        int index = StdRandom.uniformInt(n);
        Item item = items[index];
        n--;
        items[index] = items[n];
        items[n] = null;
        if (n > 0 && (items.length / 4 >= n)) {
            resize(items.length / 4);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("n");
        int index = StdRandom.uniformInt(n);
        return items[index];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] copy;
        private int index = 0;

        public RandomIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = items[i];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            if (index >= copy.length) return false;
            return true;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("ns");
            Item i = copy[index];
            index++;
            return i;
        }

        public void remove() {
            throw new UnsupportedOperationException("bs");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("-isempty " + rq.isEmpty());

        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");
        System.out.println("-initial " + rq.size());

        System.out.println("-dequeue " + rq.dequeue());

        for (String s : rq) {
            System.out.println("  " + s);
        }

        System.out.println("-dequeued size " + rq.size());

        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());

        System.out.println("-random iterator");
        for (String s : rq) {
            System.out.println(s);
        }

        System.out.println("-random iterator one more time");
        for (String s : rq) {
            System.out.println(s);
        }
    }

}

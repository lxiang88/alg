/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node front;
    private Node back;
    private int n;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        front = null;
        back = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (n == 0) {
            return true;
        }
        return false;
    }


    // return the number of items on the deque
    public int size() {
        return this.n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("barg");
        }
        if (front == null) {
            Node newfront = new Node();
            newfront.item = item;
            newfront.next = null;
            newfront.prev = null;
            front = newfront;
            back = front;
        }
        else {
            Node newfront = new Node();
            newfront.item = item;
            newfront.next = null;
            newfront.prev = front;
            front.next = newfront;
            front = newfront;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("barg");
        }
        if (back == null) {
            Node newback = new Node();
            newback.item = item;
            newback.next = null;
            newback.prev = null;
            back = newback;
            front = back;
        }
        else {
            Node newback = new Node();
            newback.item = item;
            newback.prev = null;
            newback.next = back;
            back.prev = newback;
            back = newback;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n <= 0) {
            throw new NoSuchElementException("narg");
        }
        Item item = front.item;
        if (n == 1) {
            front = null;
            back = null;
        }
        else {
            front.prev.next = null;
            front = front.prev;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n <= 0) {
            throw new NoSuchElementException("narg");
        }
        Item item = back.item;
        if (n == 1) {
            front = null;
            back = null;
        }
        else {
            back = back.next;
            back.prev = null;

        }
        n--;
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator(front);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node initialNode) {
            current = initialNode;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.prev;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    // unit testing (required)

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.print(deque.removeLast() + "\n");
        System.out.print(deque.removeLast() + "\n");

    }
}


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // unit testing (required)
    public static void main(String[] args) {
        final Deque<Integer> deque = new Deque<>();
        StdOut.println("The deque is empty: " + deque.isEmpty());
        deque.addFirst(1);
        deque.addLast(2);
        StdOut.println("Current size: " + deque.size);
        StdOut.println("The deque is empty: " + deque.isEmpty());
        StdOut.println("Remove first: " + deque.removeFirst());
        StdOut.println("Remove last: " + deque.removeLast());
        StdOut.println("Current size: " + deque.size);

        for (int i = 1; i <= 3; i++) {
            deque.addFirst(i);
            deque.addLast(-i);
        }
        StdOut.println("Current size: " + deque.size);
        for (int value : deque) {
            StdOut.println("value : " + value);
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return 0 == size;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            head = new Node<Item>(item);
            tail = head;
        } else {
            head = new Node<>(item, head, null);
            head.next.previous = head;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            head = new Node<Item>(item);
            tail = head;
        } else {
            tail = new Node<>(item, null, tail);
            tail.previous.next = tail;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Item it = head.item;
        if (head == tail) {
            head = null;
            tail = null;
        } else if (head != null) {
            head = head.next;
            head.previous = null;
        }
        size--;
        return it;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final Item it = tail.item;
        if (head == tail) {
            head = null;
            tail = null;
        } else if (tail != null) {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return it;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Item it = currentNode.item;
            currentNode = currentNode.next;
            return it;
        }
    }

    private class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> previous;

        public Node() {
            this(null);
        }

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.previous = null;
        }

        public Node(Item item, Node<Item> next, Node<Item> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }
}

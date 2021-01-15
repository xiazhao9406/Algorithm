import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 4;
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // unit testing (required)
    public static void main(String[] args) {
        final RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }
        StdOut.println("Queue size: " + randomizedQueue.size());
        for (int i = 0; i < 5; i++) {
            StdOut.println("Sample: " + randomizedQueue.sample());
        }

        for (int i = 0; i < 10; i++) {
            StdOut.println("Dequeue: " + randomizedQueue.dequeue());
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return 0 == size;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final int index = StdRandom.uniform(size);
        final Item it = items[index];
        items[index] = items[--size];
        items[size] = null;
        if (size > DEFAULT_SIZE && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return it;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final int index = StdRandom.uniform(size);
        return items[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int capacity) {
        Item[] oldItems = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < Math.min(items.length, oldItems.length); i++) {
            items[i] = oldItems[i];
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] tempItems = (Item[]) new Object[size];
        private int tempIndex = 0;

        RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                tempItems[i] = items[i];
            }
            for (int i = 0; i < size; i++) {
                final int index = StdRandom.uniform(i, size);
                final Item it = tempItems[index];
                tempItems[index] = tempItems[i];
                tempItems[i] = it;
            }
        }

        @Override
        public boolean hasNext() {
            return tempIndex != size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return tempItems[tempIndex++];
        }
    }
}
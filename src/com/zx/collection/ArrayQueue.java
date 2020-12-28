package com.zx.collection;

import java.util.Iterator;

public class ArrayQueue<Item> implements Queue<Item>{
    private final int DEFAULT_CAPACITY = 4;
    private Item[] items = (Item[]) new Object[DEFAULT_CAPACITY];
    private int head = 0;
    private int tail = 0;

    @Override
    public void enqueue(Item item) {
        if (tail == items.length) {
            resize(Math.max(((tail - head) * 2), DEFAULT_CAPACITY));
        }
        items[tail++] = item;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return items[head++];
    }

    @Override
    public boolean isEmpty() {
        return 0 == tail - head;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<Item> {
        int currentIndex = head;

        @Override
        public boolean hasNext() {

            return currentIndex < tail;
        }

        @Override
        public Item next() {
            return items[currentIndex++];
        }
    }

    private void resize(int capacity) {
        Item[] oldItems = items;
        items = (Item[]) new Object[capacity];
        int count = tail - head;
        for (int i = 0; i < count; i++){
            items[i] = oldItems[i + head];
        }
        head = 0;
        tail = count;
    }
}

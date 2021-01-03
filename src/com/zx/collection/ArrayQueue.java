package com.zx.collection;

import java.util.Iterator;

public class ArrayQueue<Item> implements Queue<Item> {
    private final static int DEFAULT_SIZE = 4;
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];
    private int head = 0;
    private int tail = 0;

    @Override
    public void enqueue(Item item) {
        if (tail == items.length) {
            resize(Math.max((tail - head) * 2, DEFAULT_SIZE));
        }
        items[tail++] = item;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        final Item it = items[head];
        if (head == tail) {
            head = 0;
            tail = 0;
        } else {
            head++;
        }
        return it;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentIndex = head;

            @Override
            public boolean hasNext() {
                return currentIndex < tail;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }
                return items[currentIndex++];
            }
        };
    }

    private void resize(int capacity) {
        final Item[] oldItems = items;
        final int size = tail - head;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            items[i] = oldItems[i + head];
        }
        head = 0;
        tail = size;
    }
}

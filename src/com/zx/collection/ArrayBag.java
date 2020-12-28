package com.zx.collection;

import java.util.Iterator;

public class ArrayBag<Item> implements Bag<Item> {
    private static final int DEFAULT_CAPACITY = 4;
    private Item[] items = (Item[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(Item item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
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
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < oldItems.length; i++) {
            items[i] = oldItems[i];
        }
    }
}

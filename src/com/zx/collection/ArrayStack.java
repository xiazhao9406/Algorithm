package com.zx.collection;

import java.util.Iterator;

public class ArrayStack<Item> implements Stack<Item> {
    private static final int DEFAULT_CAPACITY = 4;
    private Item[] items = (Item[]) new Object[DEFAULT_CAPACITY];
    private int top = 0;

    @Override
    public void push(Item item) {
        if (top == items.length) {
            resize(items.length * 2);
        }
        items[top++] = item;
    }

    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        final Item it = items[--top];
        if (items.length > DEFAULT_CAPACITY && top == items.length / 4) {
            resize(items.length / 2);
        }
        return it;
    }

    @Override
    public boolean isEmpty() {
        return 0 == top;
    }

    @Override
    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return items[top - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < top;
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
        for (int i = 0; i < Math.min(items.length, oldItems.length); i++) {
            items[i] = oldItems[i];
        }
    }
}

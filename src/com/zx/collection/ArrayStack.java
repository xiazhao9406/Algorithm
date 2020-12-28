package com.zx.collection;

import java.util.Iterator;

public class ArrayStack<Item> implements Stack<Item>{
    private final int DEFAULT_CAPACITY = 4;
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
        if (top < items.length / 4) {
            resize(items.length / 2);
        }
        if (0 == top) {
            throw new IllegalStateException();
        }
        return items[--top];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ArrayStackIterator implements Iterator<Item> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < top;
        }

        @Override
        public Item next() {
            return items[currentIndex++];
        }
    }

    private void resize(int capacity) {
        Item[] oldItems = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < Math.min(oldItems.length, items.length); i++) {
            items[i] = oldItems[i];
        }
    }

}

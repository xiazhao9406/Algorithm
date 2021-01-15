package com.zx.collection;

import java.util.Comparator;

public class UnorderedPriorityQueue<Item extends Comparable<Item>> implements PriorityQueue<Item> {
    private static final int DEFAULT_SIZE = 16;
    private Item[] items;
    private Comparator comparator;
    private int size;

    public UnorderedPriorityQueue() {
        this(null);
    }

    public UnorderedPriorityQueue(Comparator comparator) {
        this.comparator = comparator;
        items = (Item[]) new Comparable[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void insert(Item item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    @Override
    public Item max() {
        if (isEmpty()) throw new IllegalStateException();
        return items[getMaxIndex()];
    }

    @Override
    public Item delMax() {
        if (isEmpty()) throw new IllegalStateException();
        int maxIndex = getMaxIndex();
        Item it = items[maxIndex];
        size--;
        if (size < items.length / 4) {
            resize(Math.max(items.length / 2, DEFAULT_SIZE));
        }
        for (int i = maxIndex; i < size; i++) {
            items[i] = items[i + 1];
        }
        return it;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        if (capacity == items.length) return;
        final Item[] oldItems = items;
        items = (Item[]) new Comparable[capacity];
        for (int i = 0; i < Math.min(items.length, oldItems.length); i++) {
            items[i] = oldItems[i];
        }
    }

    private int getMaxIndex() {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            int cmp = comparator != null ? comparator.compare(items[i], items[maxIndex]) : items[i].compareTo(items[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

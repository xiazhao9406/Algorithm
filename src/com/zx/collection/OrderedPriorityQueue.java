package com.zx.collection;

import java.util.Comparator;


public class OrderedPriorityQueue<Item extends Comparable<Item>> implements PriorityQueue<Item> {
    private static final int DEFAULT_SIZE = 16;
    private final Comparator comparator;
    private Item[] items;
    private int size;

    public OrderedPriorityQueue() {
        this(null);
    }

    public OrderedPriorityQueue(Comparator comparator) {
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
        for (int i = size - 1; i > 0 && compareIn(i, i - 1); i--) {
            final Item it = items[i];
            items[i] = items[i - 1];
            items[i - 1] = it;
        }
    }

    @Override
    public Item max() {
        if (isEmpty()) throw new IllegalStateException();
        return items[size - 1];
    }

    @Override
    public Item delMax() {
        if (isEmpty()) throw new IllegalStateException();
        Item it = items[--size];
        if (size < items.length / 4) {
            resize(Math.max(items.length / 2, DEFAULT_SIZE));
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

    private boolean compareIn(int i, int j) {
        return (comparator != null ? comparator.compare(items[i], items[j]) : items[i].compareTo(items[j])) < 0;
    }

    private void resize(int capacity) {
        if (capacity == items.length) return;
        final Item[] oldItems = items;
        items = (Item[]) new Comparable[capacity];
        for (int i = 0; i < Math.min(items.length, oldItems.length); i++) {
            items[i] = oldItems[i];
        }
    }
}

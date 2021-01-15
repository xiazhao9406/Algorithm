package com.zx.collection;

import java.util.Comparator;

import static com.zx.sort.SortUtil.swap;

public class HeapPriorityQueue<Item extends Comparable<Item>> implements PriorityQueue<Item> {
    private static final int DEFAULT_SIZE = 16;
    private final Comparator comparator;
    private Item[] items;
    private int size;

    public HeapPriorityQueue() {
        this((Comparator) null, null);
    }

    public HeapPriorityQueue(Comparator comparator) {
        this(comparator, null);
    }

    public HeapPriorityQueue(Item[] items) {
        this(null, items);
    }

    public HeapPriorityQueue(Comparator comparator, Item[] items) {
        this.comparator = comparator;
        if (items != null) {
            this.items = (Item[]) new Comparable[items.length];
            for (int i = 0; i < items.length; i++) {
                this.items[i] = items[i];
            }
            size = items.length;
            for (int i = (size - 2) / 2; i >= 0; i--) {
                sink(i);
            }
        } else {
            this.items = (Item[]) new Comparable[DEFAULT_SIZE];
            size = 0;
        }
    }

    @Override
    public void insert(Item item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
        swim(size - 1);
    }

    @Override
    public Item max() {
        if (isEmpty()) throw new IllegalStateException();
        return items[0];
    }

    @Override
    public Item delMax() {
        if (isEmpty()) throw new IllegalStateException();
        final Item it = items[0];
        swap(items, 0, size - 1);
        size--;
        sink(0);
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

    private void sink(int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        if (left < size) {
            if (right < size && larger(right, left) && larger(right, parent)) {
                swap(items, right, parent);
                sink(right);
            } else if (larger(left, parent)) {
                swap(items, left, parent);
                sink(left);
            }
        }
    }

    private void swim(int child) {
        if (child > 0) {
            if (larger(child, (child - 1) / 2)) {
                swap(items, child, ((child - 1) / 2));
                child = (child - 1) / 2;
                swim(child);
            }
        }
    }

    private void resize(int capacity) {
        if (capacity == items.length) return;
        final Item[] oldItems = items;
        items = (Item[]) new Comparable[capacity];
        for (int i = 0; i < Math.min(items.length, oldItems.length); i++) {
            items[i] = oldItems[i];
        }
    }

    private boolean larger(int i, int j) {
        return (comparator != null ? comparator.compare(items[i], items[j]) : items[i].compareTo(items[j])) > 0;
    }
}

package com.zx.collection;

import java.util.Comparator;

public class HeapPriorityQueue<Item extends Comparable<Item>> implements priorityQueue<Item>{

    private static final int DEFAULT_SIZE = 16;
    private final Comparator<Item> comparator;
    private Item[] items;
    private int size;

    public HeapPriorityQueue() {
        comparator = null;
    }

    public HeapPriorityQueue(Comparator comparator) {
        this.comparator = comparator;
        items = (Item[]) new Comparable[DEFAULT_SIZE];
        size = 0;
    }

    public  HeapPriorityQueue(Item[] data) {
        this(data, null);
    }

    public HeapPriorityQueue(Item[] data, Comparator<Item> comparable) {
        this(comparable);
        items = data.clone();
        size = items.length;
        for (int i = size / 2; i >= 0; i--) sink(i);

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
        if (isEmpty()) throw new IllegalArgumentException();
        return items[0];
    }

    @Override
    public Item delMax() {
        if (isEmpty()) throw new IllegalArgumentException();
        final Item item = items[0];
        items[0] = items[--size];
        items[size] = null;
        sink(0);
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int left(int root) {
        return 2 * root + 1;
    }

    private int right(int  root) {
        return 2 * (root + 1);
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }

    private void swap(int i, int j) {
        final Item item = items[i];
        items[i] = items[j];
        items[j] = item;
    }


    private boolean less(int thiz, int that) {
        return (comparator != null ? comparator.compare(items[thiz], items[that]) : items[thiz].compareTo(items[that])) < 0;
    }

    private void sink(int parent) {
        final int left = left(parent), right = right(parent);
        if (left < size) {
            if (right < size && less(left, right) && less(parent, right)) {
                swap(parent, right);
                sink(right);
            } else if (less(parent, left)) {
                swap(parent, left);
                sink(left);
            }
        }
    }

    private void swim(int child) {
        if (child > 0) {
            int parent = parent(child);
            if (less(parent, child)) {
                swap(parent, child);
                swim(parent);
            }
        }
    }


    private void resize(int capacity) {
        final Item[] oldItems = items;
        items = (Item[]) new Comparable[capacity];
        for (int i = 0; i < Math.min(oldItems.length, size); i++) {
            items[i] = oldItems[i];
        }
    }
}

package com.zx.collection;

import com.zx.sort.SortUtil;

import java.util.Comparator;

import static com.zx.sort.SortUtil.less;

public class OrderedPriorityQueue<Item extends Comparable<Item>> implements priorityQueue<Item>{
    private static final int DEFAULT_SIZE = 16;
    private Item[] items;
    private final Comparator comparator;
    private int size;


    public OrderedPriorityQueue() {
        this(null);
    }

    public OrderedPriorityQueue(Comparator comparator) {
        this.comparator = comparator;
        items = (Item[]) new Comparator[DEFAULT_SIZE];
        size = 0;

    }

    @Override
    public void insert(Item item) {
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[size++] = item;
        for (int i = size - 1; i >= 1 && SortUtil.less(items[i], items[i - 1]); i--) {
            SortUtil.swap(items, i, i - 1);
        }
    }


    @Override
    public Item max() {
        if (isEmpty()) throw new IllegalArgumentException();
        return items[size - 1];
    }

    @Override
    public Item delMax() {
        if (isEmpty()) throw new IllegalArgumentException();
        final Item item = items[--size];
        if (size < items.length / 4) {
            resize(Math.max(items.length / 2, DEFAULT_SIZE));
        }
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

    private void resize(int capacity) {
        if (items.length == capacity) return;
        final Item[] oldItems = items;
        items = (Item[]) new Comparator[capacity];
        for (int i = 0; i < Math.min(oldItems.length, items.length); i++) {
            items[i] = oldItems[i];
        }
    }
}

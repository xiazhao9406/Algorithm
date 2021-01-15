package com.zx.collection;

public interface PriorityQueue<Item extends Comparable<Item>> {
    void insert(Item item);

    Item max();

    Item delMax();

    boolean isEmpty();

    int size();
}

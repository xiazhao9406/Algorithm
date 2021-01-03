package com.zx.collection;

public interface Bag<Item> extends Iterable<Item> {
    void add(Item item);

    int size();
}

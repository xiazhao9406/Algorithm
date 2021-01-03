package com.zx.collection;

public interface Stack<Item> extends Iterable<Item> {
    void push(Item item);

    Item pop();

    boolean isEmpty();

    Item peek();
}

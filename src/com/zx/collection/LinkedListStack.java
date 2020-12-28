package com.zx.collection;

import java.util.Iterator;

public class LinkedListStack<Item> implements Stack<Item> {
    private Node<Item> top = null;

    @Override
    public void push(Item item) {
        top = new Node<>(item, top);
    }

    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        final Item it = top.item;
        top = top.next;
        return it;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return top.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node<Item> currentNode = top;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }
                final Item it = currentNode.item;
                currentNode = currentNode.next;
                return it;
            }
        };
    }
}

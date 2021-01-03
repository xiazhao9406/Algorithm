package com.zx.collection;

import java.util.Iterator;

public class LinkedListQueue<Item> implements Queue<Item> {
    private Node<Item> head = null;
    private Node<Item> tail = null;

    @Override
    public void enqueue(Item item) {
        final Node<Item> it = new Node<>(item);
        if (head == null) {
            head = it;
        } else {
            tail.next = it;
        }
        tail = it;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        final Item it = head.item;
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            head = head.next;
        }
        return it;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node<Item> currentNode = head;

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

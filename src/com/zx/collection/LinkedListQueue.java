package com.zx.collection;

import java.util.Iterator;

public class  LinkedListQueue<Item> implements Queue<Item>{
    private Node<Item> head = null;
    private Node<Item> tail = null;


    @Override
    public void enqueue(Item item) {
        final Node<Item> oldTail = tail;
        tail = new Node(item);
        if (oldTail != null) {
            oldTail.next = tail;
        }
        if (head == null) {
            head = tail;
        }
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        final Item it = head.item;
        if (head == tail) {
            head = tail = null;
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
        return new LinkedListQueueIterator();
    }

    private class LinkedListQueueIterator implements Iterator<Item> {
        Node<Item> currentNode = head;

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public Item next() {
            Item it = currentNode.item;
            currentNode = currentNode.next;
            return it;
        }
    }
}

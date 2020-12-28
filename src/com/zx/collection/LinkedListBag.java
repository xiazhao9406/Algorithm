package com.zx.collection;

import java.util.Iterator;

public class LinkedListBag<Item> implements Bag<Item>{
    private Node<Item> first = null;
    private int size = 0;

    @Override
    public void add(Item item) {
        first = new Node<>(item, first);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListBagIterator();
    }

    private class LinkedListBagIterator implements Iterator<Item> {
        Node<Item> currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            Item it = currentNode.item;
            currentNode = currentNode.next;
            return it;
        }
    }

}

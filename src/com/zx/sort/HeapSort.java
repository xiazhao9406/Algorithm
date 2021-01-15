package com.zx.sort;

import com.zx.collection.HeapPriorityQueue;
import com.zx.collection.PriorityQueue;

import static com.zx.sort.SortUtil.less;
import static com.zx.sort.SortUtil.swap;

public class HeapSort {
    public static <Item extends Comparable<Item>> void sortWithPriorityQueue(Item[] items) {
        if (items == null || items.length <= 1) return;
        final PriorityQueue<Item> pq = new HeapPriorityQueue<>(items);
        for (int i = items.length - 1; i >= 0; i--) {
            items[i] = pq.delMax();
        }
    }

    public static void sort(Comparable[] items) {
        if (items == null || items.length <= 1) return;
        int size = items.length;
        for (int i = (size - 2) / 2; i >= 0; i--) {
            sink(items, size, i);
        }
        while (size > 0) {
            SortUtil.swap(items, 0, --size);
            sink(items, size, 0);
        }
    }

    private static void sink(Comparable[] items, int size, int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        if (left < size) {
            if (right < size) {
                if (less(items[right], items[left])) {
                    if (less(items[parent], items[left])) {
                        swap(items, left, parent);
                        sink(items, size, left);
                    }
                } else if (less(items[parent], items[right])) {
                    swap(items, right, parent);
                    sink(items, size, right);
                }
            } else if (less(items[parent], items[left])) {
                swap(items, left, parent);
                sink(items, size, left);
            }
        }
    }
}

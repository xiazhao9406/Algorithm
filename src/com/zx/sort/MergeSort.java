package com.zx.sort;

public class MergeSort {

    public static <Item extends Comparable<Item>> void sort(Item[] items) {
        if (items == null || items.length <= 1) {
            return;
        }
        final Item[] aux = (Item[]) new Comparable[items.length];
        sortInternal(items, aux, 0, items.length - 1);
    }

    public static <Item extends Comparable<Item>> void bottomUpSort(Item[] items) {
        if (items == null || items.length <= 1) {
            return;
        }
        final Item[] aux = (Item[]) new Comparable[items.length];
        for (int sz = 1; sz < items.length; sz += sz) {
             for (int i = 0; i < items.length; i += sz * 2) {
                 merge(items, aux, i, Math.min(i + sz - 1, items.length), Math.min(i + sz * 2 - 1, items.length - 1));
             }
        }
    }

    private static <Item extends Comparable<Item>> void merge(Item[] items, Item[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = items[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) items[k] = aux[j++];
            else if (j > hi) items[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i])) items[k] = aux[j++];
            else items[k] = aux[i++];
        }
    }

    private static <Item extends Comparable<Item>> void sortInternal(Item[] items, Item[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        final int mid = (lo + hi) / 2;
        sortInternal(items, aux, lo, mid);
        sortInternal(items, aux, mid + 1, hi);
        merge(items, aux, lo, mid, hi);
    }
}

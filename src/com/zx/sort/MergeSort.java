package com.zx.sort;

public class MergeSort {
    public static void sort(Comparable[] items) {
        if (items == null || items.length <= 1) return;
        final Comparable[] aux = new Comparable[items.length];
        sortInternal(items, aux, 0, items.length - 1);
    }

    public static void bottomUpSort(Comparable[] items) {
        if (items == null || items.length <= 1) return;
        final Comparable[] aux = new Comparable[items.length];
        for (int sz = 1; sz < items.length; sz *= 2) {
            for (int lo = 0; lo < items.length; lo += 2 * sz) {
                final int mid = lo + sz - 1;
                final int hi = lo + 2 * sz - 1;
                merge(items, aux, lo, Math.min(items.length - 1, mid), Math.min(items.length - 1, hi));
            }
        }
    }

    private static void sortInternal(Comparable[] items, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        final int mid = (lo + hi) / 2;
        sortInternal(items, aux, lo, mid);
        sortInternal(items, aux, mid + 1, hi);
        merge(items, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] items, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = items[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                items[k] = aux[j++];
            } else if (j > hi) {
                items[k] = aux[i++];
            } else if (SortUtil.less(aux[j], aux[i])) {
                items[k] = aux[j++];
            } else {
                items[k] = aux[i++];
            }
        }
    }
}

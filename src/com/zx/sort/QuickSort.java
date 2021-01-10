package com.zx.sort;

public class QuickSort {
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) return;
        SortUtil.shuffle(a);
        quickSort(a, 0, a.length - 1);
    }

    public static void quickThreeWaySort(Comparable[] a) {
        threeWaySort(a, 0, a.length - 1);
    }

    private static void threeWaySort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo;
        int i = lo;
        int gt = hi;
        final Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                SortUtil.swap(a, lt++, i++);
            } else if (cmp > 0) {
                SortUtil.swap(a, gt--, i);
            } else {
                i++;
            }
        }
        threeWaySort(a, lo, lt - 1);
        threeWaySort(a, gt + 1, hi);
    }


    private static void quickSort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        final int i = partition(a, lo, hi);
        quickSort(a, lo, i - 1);
        quickSort(a, i + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        final Comparable v = a[lo];
        while (i <= j) {
            int cmp = a[i].compareTo(v);
            if (cmp <= 0) {
                i++;
            } else {
                SortUtil.swap(a, i, j--);
            }
        }
        SortUtil.swap(a, lo, j);
        return j;
    }
}

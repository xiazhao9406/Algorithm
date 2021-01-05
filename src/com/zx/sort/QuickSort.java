package com.zx.sort;

public class QuickSort {
    public static void sort(Comparable[] a) {
        SortUtil.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void QuickThreeWaySort(Comparable[] a) {
        threeWaySort(a, 0, a.length - 1);
    }

    private static void threeWaySort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) SortUtil.swap(a, lt++, i++);
            else if (cmp > 0) SortUtil.swap(a, i, gt--);
            else i++;
        }
        threeWaySort(a, lo, lt - 1);
        threeWaySort(a, gt + 1, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (SortUtil.less(a[++i], v)) if (i == hi) break;
            while (SortUtil.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            SortUtil.swap(a, i, j);
        }
        SortUtil.swap(a, lo, j);
        return j;
    }
}

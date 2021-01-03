package com.zx.sort;

public class ShellSort {
    public static <Item extends Comparable<Item>> void sort(Item[] items) {
        if (items == null || items.length <= 1) {
            return;
        }
        int h = 1;
        while (h < items.length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int k = 0; k < h; k++) {
                for (int i = k; i < items.length; i += h) {
                    for (int j = i; j >= h && SortUtil.less(items[j], items[j - h]); j -= h) {
                        SortUtil.swap(items, j, j - h);
                    }
                }
            }
            h /= 3;
        }
    }
}

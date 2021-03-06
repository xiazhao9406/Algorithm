package com.zx.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShellSortTest {
    private Integer[] items;

    @BeforeEach
    void setUp() {
        items = new Integer[10000];
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
        SortUtil.shuffle(items);
    }

    @Test
    void testSort() {
        assertFalse(SortUtil.isSorted(items));
        ShellSort.sort(items);
        assertTrue(SortUtil.isSorted(items));
    }


}
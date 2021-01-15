package com.zx.unionfind;

import org.junit.jupiter.api.Test;

class WeightedUnionFindTest {

    private WeightedUnionFind weightedUnionFindTest;

    @Test
    void testLargeAmountOfUnionCall() {
        final int n = 10000000;
        weightedUnionFindTest = new WeightedUnionFind(n);
        for (int i = 1; i < n / 2; i *= 2) {
            for (int j = i; j < n; j += i) {
                weightedUnionFindTest.union(j - i, j);
            }
        }
    }

    @Test
    void testLargeAmountOfUnionCall2() {
        final int n = 10000000;
        final int group = 1000;
        weightedUnionFindTest = new WeightedUnionFind(n);
        for (int i = 0; i < n / group; i++) {
            for (int j = group * (i + 1) - 1; j > group * i; j--) {
                weightedUnionFindTest.union(j, j - 1);
            }
        }
        for (int i = 1; i < n / 2; i *= 2) {
            for (int j = i; j < n; j += i) {
                weightedUnionFindTest.union(j - i, j);
            }
        }
    }
}
package com.zx.unionfind;

import org.junit.jupiter.api.Test;

class UnionFindTest {

    private UnionFind unionFind;

    @Test
    void testLargeAmountOfUnionCall() {
        final int n = 100000;
        unionFind = new UnionFind(n);
        for (int i = 1; i < n / 2; i *= 2) {
            for (int j = i; j < n; j += i) {
                unionFind.union(j - i, j);
            }
        }
    }
}
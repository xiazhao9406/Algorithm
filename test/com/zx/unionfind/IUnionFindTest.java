package com.zx.unionfind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IUnionFindTest {

    private IUnionFind unionFind;

    @Test
    void testUnionFind() {
        unionFind = new UnionFind(10);
        initializeUnionFind();
        assets();
    }

    @Test
    void testQuickUnionFind() {
        unionFind = new QuickUnionFind(10);
        initializeUnionFind();
        assets();
    }

    @Test
    void testWeightedUnionFind() {
        unionFind = new WeightedUnionFind(10);
        initializeUnionFind();
        assets();
    }

    private void initializeUnionFind() {
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(5, 6);
        unionFind.union(7, 8);
        unionFind.union(0, 5);
        unionFind.union(7, 9);
        unionFind.union(2, 8);
        unionFind.union(1, 9);
    }

    private void assets() {
        assertTrue(unionFind.connected(1, 9));
        assertFalse(unionFind.connected(1, 5));
        assertFalse(unionFind.connected(2, 3));
        assertFalse(unionFind.connected(0, 7));
        assertTrue(unionFind.connected(2, 7));
    }
}
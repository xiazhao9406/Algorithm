package com.zx.unionfind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionFindTest {

    private QuickUnionFind quickUnionFind;

    @Test
    void testLargeAmountOfUnionCall() {
        final int n = 10000000;
        quickUnionFind = new QuickUnionFind(n);
        for (int i = 1; i < n / 2; i *= 2) {
            for (int j = i; j < n; j += i) {
                quickUnionFind.union(j - i, j);
            }
        }
    }

    @Test
    void testLargeAmountOfUnionCall2() {
        final int n = 10000000;
        final int group = 1000;
        quickUnionFind = new QuickUnionFind(n);
        for (int i = 0; i < n / group; i++) {
            for (int j = group * (i + 1) - 1; j > group * i; j--) {
                quickUnionFind.union(j, j - 1);
            }
        }
        for (int i = 1; i < n / 2; i *= 2) {
            for (int j = i; j < n; j += i) {
                quickUnionFind.union(j - i, j);
            }
        }
    }
}
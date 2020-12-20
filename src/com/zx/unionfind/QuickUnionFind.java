package com.zx.unionfind;

public class QuickUnionFind implements IUnionFind {
    private final int[] parents;

    private long basicOpCount = 0;

    public QuickUnionFind(final int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    @Override
    public boolean connected(final int p, final int q) {
        return root(p) == root(q);
    }

    @Override
    public void union(final int p, final int q) {
        final int pRoot = root(p);
        final int qRoot = root(q);
        if (pRoot != qRoot) {
            basicOpCount++;
            if (pRoot > qRoot) {
                parents[pRoot] = qRoot;
            } else {
                parents[qRoot] = pRoot;
            }
        }
    }

    private int root(int p) {
        while (p != parents[p]) {
            basicOpCount++;
            p = parents[p];
        }
        return p;
    }

    long getBasicOpCount() {
        return basicOpCount;
    }
}

package com.zx.unionfind;

public class QuickUnionFind implements IUnionFind{
    private final int[] parent;
    private int count;

    public QuickUnionFind(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return parent[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        final int rootP = find(p);
        final int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

package com.zx.unionfind;

public class UnionFind implements IUnionFind{
    private final int[] group;
    private int count;

    public UnionFind(int n) {
        group = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            group[i] = i;
        }
    }

    @Override
    public int find(int p) {
        return group[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return group[p] == group[q];
    }

    @Override
    public void union(int p, int q) {
        final int idP = group[p];
        final int idQ = group[q];
        if (idP == idQ) return;
        for (int i = 0; i < group.length; i++) {
            if (group[i] == idP) {
                group[i] = idQ;
            }
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

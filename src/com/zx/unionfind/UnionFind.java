package com.zx.unionfind;

public class UnionFind implements IUnionFind {
    private final int n;
    private final int[] group;

    private long basicOpCount = 0;

    public UnionFind(final int n) {
        this.n = n;
        group = new int[n];
        for (int i = 0; i < n; i++)
            group[i] = i;
    }

    @Override
    public boolean connected(final int p, final int q) {
        return group[p] == group[q];
    }

    @Override
    public void union(final int p, final int q) {
        final int pid = group[p];
        if (group[p] != group[q]) {
            for (int i = 0; i < n; i++) {
                if (group[i] == pid) {
                    basicOpCount++;
                    group[i] = group[q];
                }
            }
        }
    }

    long getBasicOpCount() {
        return basicOpCount;
    }
}

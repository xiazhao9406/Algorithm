package com.zx.unionfind;

public class WeightedUnionFind implements IUnionFind {
    private final int n;
    private final int[] parent;
    private final int[] weight;

    private int basicOpCount = 0;

    public WeightedUnionFind(final int n) {
        this.n = n;
        parent = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = 1;
            parent[i] = i;
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
            if (weight[pRoot] > weight[qRoot]) {
                parent[qRoot] = parent[pRoot];
                weight[pRoot] = weight[pRoot] + weight[qRoot];
            } else {
                parent[pRoot] = parent[qRoot];
                weight[qRoot] = weight[pRoot] + weight[qRoot];
            }
        }
    }

    private int root(final int p) {
        basicOpCount++;
        if (p == parent[p]) return p;
        return parent[p] = root(parent[p]);
    }

    int getBasicOpCount() {
        return basicOpCount;
    }
}

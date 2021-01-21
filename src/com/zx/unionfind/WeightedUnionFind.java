package com.zx.unionfind;

public class WeightedUnionFind implements IUnionFind{
    private final int[] parent;
    private int[] weight;
    private int count;

    public WeightedUnionFind(int n) {
        parent = new int[n];
        weight = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    @Override
    public int find(int p) {
        if (p == parent[p]) return p;
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
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
        if (weight[rootP] > weight[rootQ]) {
            parent[rootQ] = parent[rootP];
            weight[rootP] += weight[rootQ];
        } else {
            parent[rootP] = parent[rootQ];
            weight[rootQ] += weight[rootQ];
        }
        count--;
    }

    @Override
    public int count() {
        return count;
    }
}

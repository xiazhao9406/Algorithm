package com.zx.unionfind;

public interface IUnionFind {
    void union(final int p, final int q);
    boolean connected(final int p, final int q);
}

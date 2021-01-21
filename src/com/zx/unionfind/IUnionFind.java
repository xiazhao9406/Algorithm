package com.zx.unionfind;

public interface IUnionFind {
    int find(int p);
    boolean connected(int p, int q);
    void union(int p, int q);
    int count();
}

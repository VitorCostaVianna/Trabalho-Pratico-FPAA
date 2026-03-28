package ds;

import interfaces.DisjointSet;

public class NaiveDSU implements DisjointSet {
    private int[] parent;
    private long memoryAccesses;

    public NaiveDSU(int n) {
        parent = new int[n];
        memoryAccesses = 0;
        for (int i = 0; i < n; i++) {
            parent[i] = i; 
        }
    }

    @Override
    public int find(int i) {
        memoryAccesses++;
        if (parent[i] == i) {
            return i;
        }
        
        memoryAccesses++;
        return find(parent[i]); 
    }

    @Override
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            memoryAccesses++;
        }
    }

    @Override
    public long getMemoryAccesses() {
        return memoryAccesses;
    }
}
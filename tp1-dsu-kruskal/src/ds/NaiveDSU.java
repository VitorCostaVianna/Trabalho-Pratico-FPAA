package ds;

import interfaces.DisjointSet;

public class NaiveDSU implements DisjointSet {
    private int[] parent;
    private long memoryAccesses;

    public NaiveDSU(int numNodes) {
        parent = new int[numNodes];
        memoryAccesses = 0;
        for (int i = 0; i < numNodes; i++) {
            parent[i] = i; 
        }
    }

    @Override
    public int find(int i) {
        while (parent[i] != i) {
            memoryAccesses += 2;
            i = parent[i];
        }

        memoryAccesses++;
        return i;
    }

    @Override
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            memoryAccesses++;
            return true;
        }
        return false;
    }

    @Override
    public long getMemoryAccesses() {
        return memoryAccesses;
    }
}
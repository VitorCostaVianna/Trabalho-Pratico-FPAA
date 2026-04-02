package ds;

import interfaces.DisjointSet;

public class TarjanDSU implements DisjointSet {
    private int[] parent;
    private int[] rank;
    private long memoryAccesses;

    public TarjanDSU(int numNodes) {
        parent = new int[numNodes];
        rank = new int[numNodes];
        memoryAccesses = 0;
        for (int i = 0; i < numNodes; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public int find(int i) {
        memoryAccesses++;
        if (parent[i] != i) {
            memoryAccesses++;
            parent[i] = find(parent[i]); 
            memoryAccesses++;
        }
        
        memoryAccesses++;
        return parent[i];
    }

    @Override
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            memoryAccesses += 2; 
            
            if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
                memoryAccesses++; 
            } else {
                parent[rootI] = rootJ;
                memoryAccesses++; 
                
                if (rank[rootI] == rank[rootJ]) {
                    rank[rootJ]++;
                    memoryAccesses += 2; 
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public long getMemoryAccesses() {
        return memoryAccesses;
    }
}
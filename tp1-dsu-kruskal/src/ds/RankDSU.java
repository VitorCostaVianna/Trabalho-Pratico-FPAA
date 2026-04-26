package ds;

import interfaces.DisjointSet;

/**
 * DSU com otimização por rank (union by rank).
 * No {@code union}, a árvore de menor rank é anexada à de maior rank,
 * mantendo as árvores balanceadas sem compressão de caminho no {@code find}.
 */
public class RankDSU implements DisjointSet {
    private int[] parent;
    private int[] rank;
    private long memoryAccesses;

    /**
     * Inicializa a estrutura com {@code numNodes} elementos, cada um em seu próprio conjunto
     * com rank inicial 0.
     *
     * @param numNodes número de elementos
     */
    public RankDSU(int numNodes) {
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
            memoryAccesses += 2;
            
            if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
                memoryAccesses++;
            } else {
                parent[rootI] = rootJ;
                memoryAccesses++;
                
                if (rank[rootI] == rank[rootJ]) {
                    rank[rootJ]++;
                    memoryAccesses += 4;
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
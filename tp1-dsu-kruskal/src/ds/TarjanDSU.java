package ds;

import interfaces.DisjointSet;

/**
 * DSU com union by rank e compressão de caminho (path compression) de Tarjan.
 * O {@code find} realiza dois passes: primeiro localiza a raiz, depois redireciona
 * todos os nós do caminho diretamente para ela, achatando a árvore.
 */
public class TarjanDSU implements DisjointSet {
    private int[] parent;
    private int[] rank;
    private long memoryAccesses;

    /**
     * Inicializa a estrutura com {@code numNodes} elementos, cada um em seu próprio conjunto
     * com rank inicial 0.
     *
     * @param numNodes número de elementos
     */
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
        int root = i;

        while (parent[root] != root) {
            memoryAccesses += 2;
            root = parent[root];
        }

        while (parent[i] != i) {
            int next = parent[i];
            parent[i] = root;
            memoryAccesses += 3;
            i = next;
        }

        memoryAccesses += 2;
        return root;
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
package ds;

import interfaces.DisjointSet;

/**
 * DSU simples (naive) sem otimizações.
 * O {@code find} percorre a cadeia de pais iterativamente sem compressão de caminho.
 * O {@code union} sempre anexa a raiz de {@code i} como filho da raiz de {@code j}.
 */
public class NaiveDSU implements DisjointSet {
    private int[] parent;
    private long memoryAccesses;

    /**
     * Inicializa a estrutura com {@code numNodes} elementos, cada um em seu próprio conjunto.
     *
     * @param numNodes número de elementos
     */
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
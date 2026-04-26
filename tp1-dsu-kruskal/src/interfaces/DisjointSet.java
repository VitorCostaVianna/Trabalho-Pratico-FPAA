package interfaces;

/** Interface para estruturas Disjoint Set Union (DSU / Union-Find). */
public interface DisjointSet {
    /**
     * Encontra o representante (raiz) do conjunto ao qual o elemento {@code i} pertence.
     *
     * @param i índice do elemento
     * @return índice da raiz do conjunto
     */
    int find(int i);

    /**
     * Une os conjuntos dos elementos {@code i} e {@code j}.
     *
     * @param i índice do primeiro elemento
     * @param j índice do segundo elemento
     * @return {@code true} se os elementos estavam em conjuntos diferentes e foram unidos;
     *         {@code false} se já pertenciam ao mesmo conjunto
     */
    boolean union(int i, int j);

    /**
     * Retorna o total de acessos à memória realizados pelas operações desta estrutura.
     *
     * @return número de acessos à memória
     */
    long getMemoryAccesses();
}
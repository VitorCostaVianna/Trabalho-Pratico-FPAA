package interfaces;

import model.Graph;

/** Interface base para algoritmos de grafos. */
public interface Algorithms {
    /**
     * Executa o algoritmo no grafo fornecido usando a estrutura DSU especificada.
     *
     * @param graph grafo de entrada
     * @param ds    estrutura Disjoint Set Union auxiliar
     */
    static <T, K> void executar(Graph<T, K> graph, DisjointSet ds) {}
}
    

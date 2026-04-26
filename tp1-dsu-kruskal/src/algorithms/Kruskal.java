package algorithms;

import java.util.ArrayList;
import java.util.List;

import interfaces.Algorithms;
import interfaces.DisjointSet;
import model.Edge;
import model.Graph;

/** Implementação do algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (MST). */
public class Kruskal<T,K> implements Algorithms {

        /**
         * Executa o algoritmo de Kruskal no grafo fornecido.
         * Ordena as arestas por peso e adiciona cada aresta à MST se ela não formar um ciclo,
         * usando a estrutura DSU para verificar conectividade.
         *
         * @param graph grafo de entrada com nós e arestas
         * @param ds    estrutura Disjoint Set Union usada para detecção de ciclos
         * @return lista de arestas que compõem a MST
         */
        public static <T, K> List<Edge<K>> executar(Graph<T, K> graph, DisjointSet ds) {
                Edge<K>[] edges = graph.getSortedEdges();
                List<Edge<K>> mst = new ArrayList<>();

                for(Edge<K> edge : edges) {
                        int firstNode = edge.getSource().getId();
                        int secondNode = edge.getDestination().getId();

                        if(ds.union(firstNode, secondNode)) {
                                mst.add(edge);
                        }

                        if(mst.size() == graph.getNodes().length - 1) {
                                break;
                        }
                }

                return mst;
        }

}
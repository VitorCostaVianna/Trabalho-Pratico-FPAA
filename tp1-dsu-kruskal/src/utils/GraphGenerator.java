package utils;

import java.util.Random;

import model.Edge;
import model.Graph;
import model.Node;

/** Gera grafos aleatórios conexos para uso nos benchmarks. */
public class GraphGenerator {
    private static final Random random = new Random();

    /**
     * Calcula o número máximo de arestas para um grafo simples com {@code numNodes} nós.
     *
     * @param numNodes número de nós
     * @return número máximo de arestas = n*(n-1)/2
     */
    private static long maxEdges(int numNodes) {
        return ((long) numNodes * (numNodes - 1)) / 2;
    }

    /**
     * Determina o número de arestas a gerar: {@code min(3*numNodes, maxEdges)}.
     *
     * @param maxEdges limite máximo de arestas para o grafo
     * @param numNodes número de nós
     * @return número de arestas a gerar
     */
    private static int numEdges(long maxEdges, int numNodes) {
        long numEdges = numNodes * 3;
        return (int) Math.min(numEdges, maxEdges);
    }

    /**
     * Gera um array de {@code numNodes} nós com id e valor iguais ao índice.
     *
     * @param numNodes número de nós
     * @return array de nós
     */
    @SuppressWarnings("unchecked")
    private static Node<Integer>[] generateNodes(int numNodes){
        Node<Integer>[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node<>(i, i);
        }
        return nodes;
    }

    /**
     * Gera um array de arestas aleatórias garantindo conectividade do grafo.
     * As primeiras {@code n-1} arestas formam um caminho linear (garantia de conexidade);
     * as demais são geradas aleatoriamente entre pares distintos de nós.
     *
     * @param nodes    array de nós do grafo
     * @param numEdges número total de arestas a gerar
     * @return array de arestas com pesos aleatórios entre 1 e 100
     */
    @SuppressWarnings("unchecked")
    private static Edge<Integer>[] generateEdges(Node<Integer>[] nodes, int numEdges) {
        Edge<Integer>[] edges = (Edge<Integer>[]) new Edge[numEdges];
        int edgeCount = 0;
        int numNodes = nodes.length;

        // Esse for apenas garante um grafo conexo
        for (int i = 0; i < numNodes - 1 && edgeCount < numEdges; i++) {
            int weight = random.nextInt(100) + 1;
            edges[edgeCount] = new Edge<>(nodes[i], nodes[i + 1], weight);
            edgeCount++;
        }

        while (edgeCount < numEdges) {
            int u = random.nextInt(numNodes);
            int v = random.nextInt(numNodes);

            if (u == v) {
                continue;
            }

            int weight = random.nextInt(100) + 1;
            edges[edgeCount] = new Edge<>(nodes[u], nodes[v], weight);
            edgeCount++;
        }
        return edges;
    }

    /**
     * Gera um grafo conexo aleatório com {@code numNodes} nós e aproximadamente {@code 3*numNodes} arestas.
     *
     * @param numNodes número de nós do grafo
     * @return grafo gerado
     */
    public static Graph<Integer, Integer> generateGraph(int numNodes) {
        Node<Integer>[] nodes = generateNodes(numNodes);

        long maxEdges = maxEdges(numNodes);
        int numEdges = numEdges(maxEdges, numNodes);

        Edge<Integer>[] edges = generateEdges(nodes, numEdges);

        return new Graph<>(nodes, edges);
    }
}

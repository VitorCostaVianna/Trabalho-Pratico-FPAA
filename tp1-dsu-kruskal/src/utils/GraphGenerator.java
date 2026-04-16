package utils;

import java.util.Random;

import model.Edge;
import model.Graph;
import model.Node;

public class GraphGenerator {
    private static final Random random = new Random();
    
    private static long maxEdges(int numNodes) {
        return ((long) numNodes * (numNodes - 1)) / 2;
    }

    private static int numEdges(int numNodes, int avgDegree) {
        // Cada aresta se conecta a 2 nós em grafos não direcionados. 
        // Somatório dos graus = 2 * E. Logo, E = (V * avgDegree) / 2.
        long requested = ((long) numNodes * avgDegree) / 2;

        if (numNodes > 1 && requested < (numNodes - 1)) {
            requested = numNodes - 1; // Garante grafo conexo independente do grau médio.
        }

        if (requested > Integer.MAX_VALUE - 8) {
            throw new IllegalArgumentException(
                "O Limite Físico de Arrays do Java foi estourado! Você solicitou " + requested + 
                " arestas, mas Arrays no Java suportam no máximo ~2.14 bilhões."
            );
        }

        return (int) requested;
    }

    @SuppressWarnings("unchecked")
    private static Node<Integer>[] generateNodes(int numNodes){
        Node<Integer>[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node<>(i, i);
        }
        return nodes;
    }

    // Método utilitário para deduplicação (usando bitwise)
    // Cria uma chave única de 64-bits interlaçando os 32 bits de u e v. 
    // É forçado que u < v para a mesma aresta (não direcionada) gerar sempre a mesma chave.
    private static long getEdgeKey(int u, int v) {
        return u < v ? (((long) u << 32) | v) : (((long) v << 32) | u);
    }

    @SuppressWarnings("unchecked")
    private static Edge<Integer>[] generateEdges(Node<Integer>[] nodes, int numEdges) {
        Edge<Integer>[] edges = (Edge<Integer>[]) new Edge[numEdges];
        int edgeCount = 0;
        int numNodes = nodes.length;

        // --- MELHORIA 2: Deduplicação de Arestas Baseada em Primitivos ---
        // Ao invés de checar Strings custosas como "A-B", empacotamos o par (u, v)
        // em único "Long". Isso acelera a inserção e minimiza a pegada de memória do Set.
        java.util.HashSet<Long> seenEdges = new java.util.HashSet<>();

        // Esse for apenas garante um grafo conexo
        for (int i = 0; i < numNodes - 1 && edgeCount < numEdges; i++) {
            int weight = random.nextInt(100) + 1;
            edges[edgeCount] = new Edge<>(nodes[i], nodes[i + 1], weight);
            seenEdges.add(getEdgeKey(i, i + 1));
            edgeCount++;
        }

        while (edgeCount < numEdges) {
            int u = random.nextInt(numNodes);
            int v = random.nextInt(numNodes);

            if (u == v) {
                continue; // Laço recursivo p/ si mesmo vetado
            }

            long edgeKey = getEdgeKey(u, v);
            if (!seenEdges.add(edgeKey)) {
                continue; // Esta aresta já existe! Recusa de gerar grafos com múltiplas arestas (multigrafo)
            }

            int weight = random.nextInt(100) + 1;
            edges[edgeCount] = new Edge<>(nodes[u], nodes[v], weight);
            edgeCount++;
        }

        // Tira a referência da estrutura pesada de index para o Garbage Collector aliviá-la já!
        seenEdges.clear();
        seenEdges = null;

        return edges;
    }

    public static Graph<Integer, Integer> generateGraph(int numNodes, int avgDegree) {
        Node<Integer>[] nodes = generateNodes(numNodes);

        int numEdges = numEdges(numNodes, avgDegree);

        Edge<Integer>[] edges = generateEdges(nodes, numEdges);

        return new Graph<>(nodes, edges);
    }
}

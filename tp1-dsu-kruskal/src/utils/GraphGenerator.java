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

    private static int numEdges(long maxEdges, int numNodes, double density) {
        density = density > 1 ? 1 : density < 0 ? 0 : density;

        long requested = (long) (density * maxEdges);

        if (numNodes > 1 && requested < (numNodes - 1)) {
            requested = numNodes - 1;
        }

        // --- MELHORIA 1: Cap de Memória / Out Of Memory Prevention ---
        // Previne IllegalArgumentException de limites de int e também o OutOfMemory 
        // limitando o tamanho do Array de Arestas. 15 milhões de objetos 'Edge'
        // consumem em média 350~500MB de Heap, mantendo-se perfeitamente seguro nas JVM modernas
        // sem sacrificar o funcionamento dos algoritmos de Kruskal/DSU.
        final long SAFE_MEMORY_LIMIT = 15_000_000L;
        if (requested > SAFE_MEMORY_LIMIT) {
            System.err.println("[AVISO GraphGenerator] Grafo extremamente denso! Reduzindo a solicitação de " 
                + requested + " arestas para " + SAFE_MEMORY_LIMIT + " por segurança no Heap Limits (OOM).");
            requested = SAFE_MEMORY_LIMIT;
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

    public static Graph<Integer, Integer> generateGraph(int numNodes, double density) {
        Node<Integer>[] nodes = generateNodes(numNodes);

        long maxEdges = maxEdges(numNodes);
        int numEdges = numEdges(maxEdges, numNodes, density);

        Edge<Integer>[] edges = generateEdges(nodes, numEdges);

        return new Graph<>(nodes, edges);
    }
}

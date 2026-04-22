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

    private static int numEdges(long maxEdges, int numNodes) {
        long numEdges = numNodes * 3;
        return (int) Math.min(numEdges, maxEdges);
    }

    @SuppressWarnings("unchecked")
    private static Node<Integer>[] generateNodes(int numNodes){
        Node<Integer>[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node<>(i, i);
        }
        return nodes;
    }

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

    public static Graph<Integer, Integer> generateGraph(int numNodes) {
        Node<Integer>[] nodes = generateNodes(numNodes);

        long maxEdges = maxEdges(numNodes);
        int numEdges = numEdges(maxEdges, numNodes);

        Edge<Integer>[] edges = generateEdges(nodes, numEdges);

        return new Graph<>(nodes, edges);
    }
}

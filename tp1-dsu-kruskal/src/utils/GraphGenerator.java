package utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.Edge;
import model.Graph;
import model.Node;

public class GraphGenerator {
    private static final Random random = new Random();
    
    private static int maxEdges(int numNodes) {
        return numNodes * (numNodes - 1) / 2;
    }

    private static int numEdges(int maxEdges, double density) {
        density = density > 1 ? 1 : density < 0 ? 0 : density;
        return (int) (density * maxEdges);
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
        Set<String> edgeSet = new HashSet<>();
        int edgeCount = 0;
        int numNodes = nodes.length;

        while (edgeCount < numEdges) {
            int u = random.nextInt(numNodes);
            int v = random.nextInt(numNodes);

            if(u != v) {
                String edgeKey = Math.min(u, v) + "-" + Math.max(u, v);
                if (!edgeSet.contains(edgeKey)){
                    edgeSet.add(edgeKey);

                    int weight = random.nextInt(100) + 1;
                    edges[edgeCount] = new Edge<>(nodes[u], nodes[v], weight);
                    edgeCount++;
                }
            }
        }
        return edges;
    }

    public static Graph<Integer, Integer> generateGraph(int numNodes, double density) {
        Node<Integer>[] nodes = generateNodes(numNodes);

        int maxEdges = maxEdges(numNodes);
        int numEdges = numEdges(maxEdges, density);

        Edge<Integer>[] edges = generateEdges(nodes, numEdges);

        return new Graph<>(nodes, edges);
    }
}

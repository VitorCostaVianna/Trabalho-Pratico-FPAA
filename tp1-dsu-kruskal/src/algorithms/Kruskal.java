package algorithms;

import java.util.ArrayList;
import java.util.List;

import interfaces.Algorithms;
import interfaces.DisjointSet;
import model.Edge;
import model.Graph;

public class Kruskal<T,K> implements Algorithms {

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
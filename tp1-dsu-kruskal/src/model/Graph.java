package model;

import java.util.Arrays;

public class Graph<T, K> {
    private Node<T>[] nodes;
    private Edge<K>[] edges;

    public Graph(Node<T>[] nodes, Edge<K>[] edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public Node<T>[] getNodes(){
        return this.nodes;
    }

    public Edge<K>[] getEdges(){
        return this.edges;
    }

    public Edge<K>[] getSortedEdges(){
        Edge<K>[] clonedEdge = this.edges.clone();
        Arrays.sort(clonedEdge, (a, b) -> Integer.compare(a.getWeight(), b.getWeight()));
        return clonedEdge;
    }

    public void addNode(Node<T> node){
        this.nodes = Arrays.copyOf(this.nodes, this.nodes.length + 1);
        this.nodes[this.nodes.length - 1] = node;
    }

}